package ua.book.house.auth.domain.dto.response;

import lombok.Builder;

@Builder
public record AccountDto(
        Long id,
        String email
) {
}
