package ua.house.book.productservice.dao;

import jakarta.inject.Inject;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ua.house.book.productservice.config.TestHibernateConfig;
import ua.house.book.productservice.config.TestProductConfig;
import ua.house.book.productservice.domain.Currency;
import ua.house.book.productservice.domain.entity.Money;
import ua.house.book.productservice.domain.entity.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@SpringBootTest
@ContextConfiguration(classes = {TestHibernateConfig.class, TestProductConfig.class})
class ProductSortDAOImplTest {
    @Autowired
    private ProductSortDAO productSortDAO;
    @Autowired
    private ProductDAO productDAO;
    private List<Product> productList;

    @BeforeEach
    void setUp() {
        productList = new ArrayList<>();

        productList.add(Product.builder()
                               .id(1L)
                               .productName("Pizza")
                               .availableCountProducts(4)
                               .money(Money.builder()
                                           .id(null)
                                           .amount(120)
                                           .currency(Currency.UAH)
                                           .build())
                               .build());

        productList.add(Product.builder()
                               .id(2L)
                               .productName("Burger")
                               .availableCountProducts(3)
                               .money(Money.builder()
                                           .id(null)
                                           .amount(140)
                                           .currency(Currency.UAH)
                                           .build())
                               .build());
        productList.add(Product.builder()
                               .id(3L)
                               .productName("Pasta")
                               .availableCountProducts(5)
                               .money(Money.builder()
                                           .id(null)
                                           .amount(150)
                                           .currency(Currency.UAH)
                                           .build())
                               .build());

        productDAO.saveAllProducts(productList);
    }

    @AfterEach
    void tearDown() {
        productDAO.deleteAllProducts();
    }

    @Test
    void getAllProductsByAscendingOrder() {
        productList.sort(Comparator.comparing(Product::getProductName));
        Assertions.assertIterableEquals(productList, productSortDAO.getAllProductsByAscendingOrder());
    }

    @Test
    void getAllProductsByDescendingOrder() {
        productList.sort((e1, e2) -> e2.getProductName()
                                       .compareTo(e1.getProductName()));
        Assertions.assertIterableEquals(productList, productSortDAO.getAllProductsByDescendingOrder());
    }

}