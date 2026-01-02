package ua.house.book.orderservice.service;

import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;
import ua.house.book.orderservice.config.TestHibernateConfig;
import ua.house.book.orderservice.config.TestOrderConfig;
import ua.house.book.orderservice.dao.PurchaseOrderDAO;
import ua.house.book.orderservice.domain.Currency;
import ua.house.book.orderservice.domain.dto.request.OrderDTO;
import ua.house.book.orderservice.domain.dto.request.OrdersDTO;
import ua.house.book.orderservice.domain.dto.request.PurchaseDTO;
import ua.house.book.orderservice.domain.dto.response.*;
import ua.house.book.orderservice.domain.entity.Order;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration(classes = {TestHibernateConfig.class, TestOrderConfig.class})
class PurchaseOrderServiceImplTest {
    @InjectMocks
    private PurchaseOrderServiceImpl orderService;
    @Mock
    private PurchaseOrderDAO purchaseOrderDAO;
    @Mock
    private RestTemplate restTemplate;
    @Autowired
    private OrdersDTO mockOrdersDTO;
    private AccountDto userAccountDto;
    private ListProductDTO listProductDTO;
    private CardDTO cardDTO;


    @BeforeEach
    void setUp() {
        List<ProductDTO> productDTOList = List.of(ProductDTO.builder()
                                                            .id(1L)
                                                            .productName("Pizza")
                                                            .availableCountProducts(4)
                                                            .moneyDTO(MoneyDTO.builder()
                                                                              .build())
                                                            .build());
        userAccountDto = AccountDto.builder()
                                   .id(1L)
                                   .email("test")
                                   .build();

        listProductDTO = ListProductDTO.builder()
                                       .products(productDTOList)
                                       .build();

        cardDTO = CardDTO.builder()
                         .numberCard("xxxx-xxxx-xxxx-xxxx")
                         .cardEndDataMonth((short) 5)
                         .cardEndDataYear((short) 2026)
                         .cvc2("859")
                         .moneyCard(MoneyCardDTO.builder()
                                                .spendLimit(1000)
                                                .moneyDTO(MoneyDTO.builder()
                                                                  .amount(2200)
                                                                  .currency(Currency.UAH)
                                                                  .build())
                                                .build())
                         .build();
    }

    @Test
    void createListOrder() {
        Assertions.assertDoesNotThrow(() -> {
            ResponseEntity<AccountDto> responseAccountDto = new ResponseEntity<>(userAccountDto, HttpStatus.OK);

            when(restTemplate.getForEntity(
                    "http://localhost:8080/api/auth/account/" + userAccountDto.email(), AccountDto.class))
                    .thenReturn(responseAccountDto);

            ResponseEntity<ListProductDTO> responseListProductDTO = new ResponseEntity<>(listProductDTO, HttpStatus.OK);

            when(restTemplate.getForEntity(
                    "http://localhost:8086/api/core/products/read-all-products", ListProductDTO.class))
                    .thenReturn(responseListProductDTO);

            when(restTemplate.postForEntity(
                    "http://localhost:8086/api/core/products/save-all-products", listProductDTO, ListProductDTO.class))
                    .thenReturn(responseListProductDTO);

            ResponseEntity<CardDTO> responseCardDTO = new ResponseEntity<>(cardDTO, HttpStatus.OK);

            when(restTemplate.getForEntity(
                    "http://localhost:8085/api/core/cards/read-card/" + userAccountDto.id(), CardDTO.class))
                    .thenReturn(responseCardDTO);

            when(restTemplate.postForEntity(
                    "http://localhost:8085/api/core/cards/update-card", cardDTO, CardDTO.class))
                    .thenReturn(responseCardDTO);

            OrdersDTO ordersDTO = mockOrdersDTO;

            doNothing().when(purchaseOrderDAO)
                       .createListOrder(any());

            orderService.createListOrder(ordersDTO);
        });
    }
}