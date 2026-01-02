package ua.house.book.creditcardservice.domain.dto.response;

import lombok.Builder;

@Builder
public record AccountDto(
        Long id,
        String email
) {
}
