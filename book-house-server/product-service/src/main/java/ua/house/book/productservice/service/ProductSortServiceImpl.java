package ua.house.book.productservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.house.book.productservice.dao.ProductSortDAO;
import ua.house.book.productservice.domain.dto.request.ProductDTO;
import ua.house.book.productservice.domain.mapper.ProductMapper;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ProductSortServiceImpl implements ProductSortService {
    private final ProductSortDAO productSortDAO;

    @Override
    public ResponseEntity<List<ProductDTO>> getAllProductsByAscendingOrder() {
        var productList = productSortDAO.getAllProductsByAscendingOrder();
        return ResponseEntity.ok().body(ProductMapper.productListIntoProductDTOList(productList));
    }

    @Override
    public ResponseEntity<List<ProductDTO>> getAllProductsByDescendingOrder() {
        var productList = productSortDAO.getAllProductsByDescendingOrder();
        return ResponseEntity.ok().body(ProductMapper.productListIntoProductDTOList(productList));
    }
}
