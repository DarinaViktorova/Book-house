package ua.house.book.orderservice.domain.dto.response;

import lombok.Builder;

import java.util.List;
@Builder
public record ListProductDTO(
        List<ProductDTO> products
) {
}
