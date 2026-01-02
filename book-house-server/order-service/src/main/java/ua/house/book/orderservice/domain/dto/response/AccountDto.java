package ua.house.book.orderservice.domain.dto.response;

import lombok.Builder;

@Builder
public record AccountDto(
        Long id,
        String email
) {
}
