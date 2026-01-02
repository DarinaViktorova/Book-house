package ua.house.book.orderservice.dao;

import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ua.house.book.orderservice.config.TestHibernateConfig;
import ua.house.book.orderservice.config.TestOrderConfig;
import ua.house.book.orderservice.domain.dto.request.OrdersDTO;
import ua.house.book.orderservice.domain.entity.Money;
import ua.house.book.orderservice.domain.entity.Order;
import ua.house.book.orderservice.domain.entity.Purchase;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestHibernateConfig.class, TestOrderConfig.class})
class PurchaseOrderDAOIT {
    @Autowired
    private PurchaseOrderDAO purchaseOrderDAO;
    @Autowired
    private OrdersDTO mockOrdersDTO;

    @Test
    void createListOrderShouldBeFound() {
        List<Order> orders = mockOrdersDTO.getOrderDTOList()
                .stream()
                .map(orderDTO -> {
                    Order order = Order.builder()
                            .accountId(1L)
                            .orderDate(orderDTO.getOrderDate())
                            .orderTime(orderDTO.getOrderTime())
                            .build();
                    List<Purchase> listResultMappingPurchases = new ArrayList<>();
                    orderDTO.getPurchaseList()
                            .forEach(purchaseDTO -> {
                                Purchase purchase = Purchase.builder()
                                        .countPurchases(purchaseDTO.getCountPurchases())
                                        .purchaseName(purchaseDTO.getPurchaseName())
                                        .money(Money.builder()
                                                .amount(purchaseDTO.getMoneyDTO()
                                                        .getAmount())
                                                .currency(purchaseDTO.getMoneyDTO()
                                                        .getCurrency())
                                                .build())
                                        .order(order) // Set the Order association for Purchase
                                        .build();
                                listResultMappingPurchases.add(purchase);
                            });
                    order.setPurchaseList(listResultMappingPurchases);
                    return order;
                })
                .toList();

        purchaseOrderDAO.createListOrder(orders);

        List<Order> listOrderFound = purchaseOrderDAO.getListOrder(1L);
        Assertions.assertEquals(2, listOrderFound.size());
    }


}