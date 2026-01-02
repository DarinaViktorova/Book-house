package ua.house.book.productservice.domain.mapper;

import ua.house.book.productservice.domain.dto.request.MoneyDTO;
import ua.house.book.productservice.domain.dto.request.ProductDTO;
import ua.house.book.productservice.domain.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductMapper {
    private ProductMapper() {
    }

    public static List<ProductDTO> productListIntoProductDTOList(List<Product> productList) {
        List<ProductDTO> productDTOList = new ArrayList<>();
        productList
                .forEach(product -> productDTOList.add(ProductDTO.builder()
                                                                 .productName(product.getProductName())
                                                                 .availableCountProducts(product.getAvailableCountProducts())
                                                                 .moneyDTO(MoneyDTO.builder()
                                                                                   .currency(product.getMoney()
                                                                                                    .getCurrency())
                                                                                   .amount(product.getMoney()
                                                                                                  .getAmount())
                                                                                   .build())
                                                                 .build()));
        return productDTOList;
    }
}
