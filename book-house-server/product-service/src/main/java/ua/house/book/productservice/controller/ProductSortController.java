package ua.house.book.productservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.house.book.productservice.domain.dto.request.ProductDTO;
import ua.house.book.productservice.service.ProductSortService;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/core/sort/products")
public class ProductSortController {
    private final ProductSortService productSortService;

    @GetMapping("/asc-orders")
    public ResponseEntity<List<ProductDTO>> ascendingOrder() {
        return productSortService.getAllProductsByAscendingOrder();
    }

    @GetMapping("/desc-orders")
    public ResponseEntity<List<ProductDTO>> descendingOrder() {
        return productSortService.getAllProductsByDescendingOrder();
    }

}

