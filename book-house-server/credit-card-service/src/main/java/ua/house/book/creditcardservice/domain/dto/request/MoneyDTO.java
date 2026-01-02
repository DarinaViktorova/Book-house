package ua.house.book.creditcardservice.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ua.house.book.creditcardservice.domain.Currency;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class MoneyDTO {
    @JsonProperty("amount")
    private Integer amount;
    @JsonProperty("currency")
    private Currency currency;
}
