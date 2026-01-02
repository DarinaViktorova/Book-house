package ua.house.book.orderservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import ua.house.book.orderservice.dao.PurchaseOrderDAO;
import ua.house.book.orderservice.domain.dto.request.OrdersDTO;
import ua.house.book.orderservice.domain.dto.response.AccountDto;
import ua.house.book.orderservice.domain.dto.response.CardDTO;
import ua.house.book.orderservice.domain.dto.response.ListProductDTO;
import ua.house.book.orderservice.domain.dto.response.ProductDTO;
import ua.house.book.orderservice.domain.entity.Order;
import ua.house.book.orderservice.domain.entity.Purchase;
import ua.house.book.orderservice.domain.mapper.PurchaseMapper;
import ua.house.book.orderservice.exception.CardNotFoundException;
import ua.house.book.orderservice.exception.OnCardHaveNotEnoughMoney;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class PurchaseOrderServiceImpl implements PurchaseOrderService {
    private final PurchaseOrderDAO purchaseOrderDAO;
    private final RestTemplate restTemplate;

    void updateCardTemplate(Optional<CardDTO> cardDto) {
        restTemplate.postForObject("http://localhost:8085/api/core/cards/update-card", cardDto, CardDTO.class);
    }

    Optional<AccountDto> findAccountTemplate(String email) {
        return Optional.ofNullable(restTemplate.getForEntity("http://localhost:8080/api/auth/account/" + email, AccountDto.class)
                                               .getBody());
    }

    Optional<CardDTO> findCardTemplate(Long idAccount) {
        return Optional.ofNullable(restTemplate.getForEntity("http://localhost:8085/api/core/cards/read-card/" + idAccount, CardDTO.class)
                                               .getBody());
    }

    List<ProductDTO> getAllProductsTemplate() {
        Optional<ListProductDTO> listProductDTO = Optional.ofNullable(restTemplate.getForEntity("http://localhost:8086/api/core/products/read-all-products", ListProductDTO.class)
                                                                                  .getBody());
        if (listProductDTO.isEmpty()) throw new IllegalStateException("Product list was empty");
        return listProductDTO.get()
                             .products();
    }

    void saveAllProductsTemplate(ListProductDTO listProductDTO) {
        restTemplate.postForEntity("http://localhost:8086/api/core/products/save-all-products", listProductDTO, ListProductDTO.class);
    }

    @Transactional
    @Override
    public void createListOrder(OrdersDTO ordersDTO) {
        Optional<AccountDto> accountDto = findAccountTemplate(ordersDTO.getAccountEmail());
        if (accountDto.isEmpty()) {
            throw new IllegalStateException("Account was not found! Email: " + ordersDTO.getAccountEmail());
        }

        List<Order> orderList = new ArrayList<>();

        ordersDTO.getOrderDTOList()
                 .forEach(orderDTO -> {
                     List<Purchase> purchaseList = PurchaseMapper.purchaseDTOListIntoPurchaseList(orderDTO.getPurchaseList());
                     orderList.add(Order.builder()
                                        .purchaseList(purchaseList)
                                        .orderDate(orderDTO.getOrderDate())
                                        .orderTime(orderDTO.getOrderTime())
                                        .accountId(accountDto.get()
                                                             .id())
                                        .build());
                 });

        var sumAllPurchases = findGeneralSumForOrders(ordersDTO);

        updateAvailableCountProduct(ordersDTO);

        Optional<CardDTO> cardDto = findCardTemplate(accountDto.get()
                                                               .id());
        if (cardDto.isEmpty()) {
            throw new CardNotFoundException("Card was not found! Email user: " + ordersDTO.getAccountEmail());
        }

        var currentAmountIntoCard = cardDto.get()
                                           .getMoneyCard()
                                           .getMoneyDTO()
                                           .getAmount();

        if (sumAllPurchases > currentAmountIntoCard)
            throw new OnCardHaveNotEnoughMoney("For creating orders u have not into card: "
                    + (sumAllPurchases - currentAmountIntoCard));


        var resultAmount = currentAmountIntoCard - sumAllPurchases;

        cardDto.get()
               .getMoneyCard()
               .getMoneyDTO()
               .setAmount(resultAmount);

        cardDto.get()
               .setAccountEmail(accountDto.get()
                                          .email());

        updateCardTemplate(cardDto);

        purchaseOrderDAO.createListOrder(orderList);
    }


    private Integer findGeneralSumForOrders(OrdersDTO ordersDTO) {
        return ordersDTO.getOrderDTOList()
                        .stream()
                        .flatMap(orderDTO -> PurchaseMapper.purchaseDTOListIntoPurchaseList(orderDTO.getPurchaseList())
                                                           .stream()
                                                           .map(purchase -> purchase.getMoney()
                                                                                    .getAmount()))
                        .reduce(Integer::sum)
                        .orElseThrow(() -> new RuntimeException("Cannot found general sum for orders!"));
    }

    private void updateAvailableCountProduct(OrdersDTO ordersDTO) {
        var productList = ordersDTO.getOrderDTOList()
                                   .stream()
                                   .flatMap(orderDTO -> PurchaseMapper.purchaseDTOListIntoPurchaseList(orderDTO.getPurchaseList())
                                                                      .stream())
                                   .flatMap(purchase -> getAllProductsTemplate()
                                           .stream()
                                           .filter(product -> purchase.getPurchaseName()
                                                                      .equals(product.getProductName()))
                                           .peek(product ->
                                                   product.setAvailableCountProducts(product.getAvailableCountProducts() - purchase.getCountPurchases())
                                           )
                                   )
                                   .toList();

        saveAllProductsTemplate(ListProductDTO.builder()
                                              .products(productList)
                                              .build());
    }


}
