package ua.book.house.auth.config;

import jakarta.inject.Inject;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
//import ua.house.book.auth.domain.entity.Account;
//import ua.house.book.product.domain.Currency;
//import ua.house.book.product.domain.dto.request.MoneyDTO;
//import ua.house.book.product.domain.dto.request.ProductDTO;
//import ua.house.book.product.domain.entity.Money;
//import ua.house.book.product.domain.entity.Order;
//import ua.house.book.product.domain.entity.Product;
//import ua.house.book.product.domain.entity.Purchase;


@Configuration
@Import(TestAuthBeansConfig.class)
public class TestCoreBeansConfig {
//    @Inject
//    private Account userAccount;
//    @Bean
//    public Product product() {
//        return Product.builder()
//                .id(1L)
//                .productName("Pizza")
//                .availableCountProducts(4)
//                .money(money())
//                .build();
//    }
//
//    @Bean
//    public ProductDTO productDTORequest(){
//        return ProductDTO.builder()
//                .id(1L)
//                .productName("Pizza")
//                .availableCountProducts(4)
//                .moneyDTO(moneyItemDTO())
//                .build();
//    }
//
//    @Bean
//    public MoneyDTO moneyItemDTO(){
//        return MoneyDTO.builder()
//                .amount(120)
//                .currency(Currency.UAH)
//                .build();
//    }
//
//    @Bean
//    public Money money() {
//        return Money.builder()
//                .id(null)
//                .amount(120)
//                .currency(Currency.UAH)
//                .build();
//    }
//
//    @Bean
//    public Product product2() {
//        return Product.builder()
//                .id(2L)
//                .productName("Burger")
//                .availableCountProducts(3)
//                .money(Money.builder()
//                        .id(null)
//                        .amount(140)
//                        .currency(Currency.UAH)
//                        .build())
//                .build();
//    }
//
//    @Bean
//    public Product product3() {
//        return Product.builder()
//                .id(3L)
//                .productName("Pasta")
//                .availableCountProducts(5)
//                .money(Money.builder()
//                        .id(null)
//                        .amount(150)
//                        .currency(Currency.UAH)
//                        .build())
//                .build();
//    }
//
//    @Bean
//    public List<Product> productList() {
//        return Arrays.asList(product(), product2(), product3());
//    }
//
//    @Bean
//    public List<Order> orderList() {
//        return Arrays.asList(
//                order1(),
//                order2()
//        );
//    }
//
//    @Bean
//    public Order order1(){
//        return Order.builder()
//                .account(userAccount)
//                .purchaseList(purchaseList())
//                .orderTime("11:11:11")
//                .orderDate("11/14/2023")
//                .build();
//    }
//    @Bean
//    public Order order2(){
//        return Order.builder()
//                .account(userAccount)
//                .purchaseList(purchaseList())
//                .orderTime("13:13:11")
//                .orderDate("10/19/2023")
//                .build();
//    }
//
//    @Bean
//    public Purchase purchase1(){
//        return Purchase.builder()
//                .id(3L)
//                .purchaseName("Pasta")
//                .countPurchases(1)
//                .money(Money.builder()
//                        .id(null)
//                        .amount(150)
//                        .currency(Currency.EUR)
//                        .build())
//                .build();
//    }
//
//    @Bean
//    public Purchase purchase2(){
//        return Purchase.builder()
//                .id(4L)
//                .purchaseName("Pizza")
//                .countPurchases(1)
//                .money(Money.builder()
//                        .id(null)
//                        .amount(150)
//                        .currency(Currency.UAH)
//                        .build())
//                .build();
//    }
//
//    @Bean
//    public Purchase purchase3(){
//        return Purchase.builder()
//                .id(5L)
//                .purchaseName("Rolls")
//                .countPurchases(1)
//                .money(Money.builder()
//                        .id(null)
//                        .amount(150)
//                        .currency(Currency.USD)
//                        .build())
//                .build();
//    }
//    @Bean
//    public List<Purchase> purchaseList() {
//        return Arrays.asList(purchase1(), purchase2(), purchase3());
//    }

}
