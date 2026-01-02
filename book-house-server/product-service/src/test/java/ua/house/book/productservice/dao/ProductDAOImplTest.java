package ua.house.book.productservice.dao;

import jakarta.inject.Inject;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ua.house.book.productservice.config.TestHibernateConfig;
import ua.house.book.productservice.config.TestProductConfig;
import ua.house.book.productservice.domain.Currency;
import ua.house.book.productservice.domain.entity.Money;
import ua.house.book.productservice.domain.entity.Product;
import ua.house.book.productservice.exception.ProductNotFoundException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ContextConfiguration(classes = {TestHibernateConfig.class, TestProductConfig.class})
class ProductDAOImplTest {
    @Inject
    private ProductDAO productDAO;
    private Product product;
    private List<Product> productList;

    @BeforeEach
    void setUp() {
        productList = new ArrayList<>();

        product = Product.builder()
                         .productName("Pizza")
                         .availableCountProducts(4)
                         .money(Money.builder()
                                     .amount(120)
                                     .currency(Currency.UAH)
                                     .build())
                         .build();

        productList.add(product);

        productList.add(Product.builder()
                               .productName("Burger")
                               .availableCountProducts(3)
                               .money(Money.builder()
                                           .amount(140)
                                           .currency(Currency.UAH)
                                           .build())
                               .build());
        productList.add(Product.builder()
                               .productName("Pasta")
                               .availableCountProducts(5)
                               .money(Money.builder()
                                           .amount(150)
                                           .currency(Currency.UAH)
                                           .build())
                               .build());

    }


    @Test
    @Order(1)
    void saveProductShouldBeFound() {
        Long idProduct = productDAO.saveProduct(product);
        Product foundProduct = productDAO.getProduct(idProduct)
                                         .orElseThrow(() -> new ProductNotFoundException("Not found product with this id: "
                                                 + product.getId()));
        System.out.println(foundProduct);
        Assertions.assertEquals(product, foundProduct);
    }


    @Test
    @Order(2)
    void updateProductShouldBeFound() {
        product.setProductName("Onion");
        Long idProduct = productDAO.updateProduct(product);
        Product foundProduct = productDAO.getProduct(idProduct)
                                         .orElseThrow(() -> new ProductNotFoundException("Not found product with this id: "
                                                 + product.getId()));
        System.out.println(foundProduct);
        Assertions.assertEquals(product, foundProduct);
        product.setProductName("Pizza");
    }

    @Test
    @Order(3)
    void deleteProductShouldNotBeFound() {
        long productId = productDAO.saveProduct(product);

        productDAO.deleteProduct(productId);

        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, () ->
                productDAO.getProduct(0L)
                          .orElseThrow(() -> new ProductNotFoundException("Not found product with this id: "
                                  + 0L)));

        // Assert the message in the exception
        String expectedMessage = "Not found product with this id: " + 0L;
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @Order(4)
    void saveAllProductsShouldBeFounds() {
        productDAO.saveAllProducts(productList);
        List<Product> foundListProducts
                = productDAO.getAllProducts();
        System.out.println(foundListProducts);
        Assertions.assertIterableEquals(productList, foundListProducts);
    }

}