package ua.house.book.productservice.dao;


import ua.house.book.productservice.domain.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDAO {
    Long saveProduct(final Product product);
    Optional<Product> getProduct(Long idProduct);
    Long updateProduct(final Product product);
    void deleteProduct(Long idProduct);
    List<Product> getAllProducts();
    void saveAllProducts(List<Product> productList);
    void deleteAllProducts();
}
