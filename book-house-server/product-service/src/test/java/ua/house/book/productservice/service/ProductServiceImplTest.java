package ua.house.book.productservice.service;

import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ua.house.book.productservice.config.TestHibernateConfig;
import ua.house.book.productservice.config.TestProductConfig;
import ua.house.book.productservice.dao.ProductDAO;
import ua.house.book.productservice.domain.Currency;
import ua.house.book.productservice.domain.dto.request.MoneyDTO;
import ua.house.book.productservice.domain.dto.request.ProductDTO;
import ua.house.book.productservice.domain.entity.Money;
import ua.house.book.productservice.domain.entity.Product;

import java.util.Optional;

@SpringBootTest
@ContextConfiguration(classes = {TestHibernateConfig.class, TestProductConfig.class})
class ProductServiceImplTest {
    @InjectMocks
    private ProductServiceImpl productService;
    @Mock
    private ProductDAO productDAO;

    private ProductDTO productDTORequest;
    private Product product;

    @BeforeEach
    void setUp() {
        productDTORequest = ProductDTO.builder()
                                      .id(1L)
                                      .productName("Pizza")
                                      .availableCountProducts(4)
                                      .moneyDTO(MoneyDTO.builder()
                                                        .amount(120)
                                                        .currency(Currency.UAH)
                                                        .build())
                                      .build();
        product = Product.builder()
                         .id(1L)
                         .productName("Pizza")
                         .availableCountProducts(4)
                         .money(Money.builder()
                                     .id(null)
                                     .amount(120)
                                     .currency(Currency.UAH)
                                     .build())
                         .build();
    }


    @Test
    void createProduct() {
        Mockito.when(productDAO.saveProduct(product))
               .thenReturn(product.getId());
        productService.createProduct(productDTORequest);
        Mockito.verify(productDAO, Mockito.times(1))
               .saveProduct(product);
    }

    @Test
    void updateProduct() {
        Mockito.when(productDAO.updateProduct(product))
               .thenReturn(product.getId());
        productService.updateProduct(productDTORequest);
        Mockito.verify(productDAO, Mockito.times(1))
               .updateProduct(Mockito.any());
    }

    @Test
    void deleteProduct() {
        Mockito.doNothing()
                .when(productDAO)
                .deleteProduct(Mockito.any());
        productService.deleteProduct(productDTORequest);
        Mockito.verify(productDAO, Mockito.times(1))
                .deleteProduct(Mockito.any());
    }

    @Test
    void readProduct() {
        Mockito.when(productDAO.
                        getProduct(Mockito.any()))
                .thenReturn(Optional.ofNullable(product));
        Assertions.assertEquals(productDTORequest, productService.readProduct(productDTORequest));
    }


}