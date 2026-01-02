package ua.house.book.productservice.domain.dto.request;

import lombok.Builder;

import java.util.List;

@Builder
public record ListProductDTO(
        List<ProductDTO> products
) {
}
