package ua.house.book.productservice.dao;


import ua.house.book.productservice.domain.entity.Product;

import java.util.List;

public interface ProductSortDAO {
    List<Product> getAllProductsByAscendingOrder();

    List<Product> getAllProductsByDescendingOrder();
}
