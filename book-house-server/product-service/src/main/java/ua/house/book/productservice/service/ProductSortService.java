package ua.house.book.productservice.service;

import org.springframework.http.ResponseEntity;
import ua.house.book.productservice.domain.dto.request.ProductDTO;

import java.util.List;

public interface ProductSortService {
    ResponseEntity<List<ProductDTO>> getAllProductsByAscendingOrder();

    ResponseEntity<List<ProductDTO>> getAllProductsByDescendingOrder();
}
