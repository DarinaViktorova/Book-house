package ua.house.book.orderservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ua.house.book.orderservice.domain.Currency;
import ua.house.book.orderservice.domain.dto.request.OrderDTO;
import ua.house.book.orderservice.domain.dto.request.OrdersDTO;
import ua.house.book.orderservice.domain.dto.request.PurchaseDTO;
import ua.house.book.orderservice.domain.dto.response.MoneyDTO;

import java.util.List;

@Configuration
@ComponentScan(basePackages = {
        "ua.house.book.orderservice.dao"
})
public class TestOrderConfig {
    @Bean
    public OrdersDTO mockOrdersDTO() {
        return OrdersDTO.builder()
                        .accountEmail("test")
                        .orderDTOList(List.of(OrderDTO.builder()
                                                      .orderDate("11/11/2002")
                                                      .orderTime("20:54:59")
                                                      .purchaseList(List.of(PurchaseDTO.builder()
                                                                                       .countPurchases(1)
                                                                                       .purchaseName("Pizza")
                                                                                       .moneyDTO(MoneyDTO.builder()
                                                                                                         .amount(120)
                                                                                                         .currency(Currency.UAH)
                                                                                                         .build())
                                                                                       .build(),
                                                              PurchaseDTO.builder()
                                                                         .countPurchases(1)
                                                                         .purchaseName("Burger")
                                                                         .moneyDTO(MoneyDTO.builder()
                                                                                           .amount(120)
                                                                                           .currency(Currency.UAH)
                                                                                           .build())
                                                                         .build()))
                                                      .build(),
                                OrderDTO.builder()
                                        .orderDate("14/12/2001")
                                        .orderTime("08:51:49")
                                        .purchaseList(List.of(PurchaseDTO.builder()
                                                                         .countPurchases(1)
                                                                         .purchaseName("Pizza")
                                                                         .moneyDTO(MoneyDTO.builder()
                                                                                           .amount(120)
                                                                                           .currency(Currency.UAH)
                                                                                           .build())
                                                                         .build(),
                                                PurchaseDTO.builder()
                                                           .countPurchases(1)
                                                           .purchaseName("Burger")
                                                           .moneyDTO(MoneyDTO.builder()
                                                                             .amount(120)
                                                                             .currency(Currency.UAH)
                                                                             .build())
                                                           .build()))
                                        .build()))
                        .build();
    }
}
