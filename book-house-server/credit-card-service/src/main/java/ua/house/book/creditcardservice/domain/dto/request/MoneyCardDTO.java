package ua.house.book.creditcardservice.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class MoneyCardDTO {
    @JsonProperty("spend_limit")
    private Integer spendLimit;
    private MoneyDTO moneyDTO;
}
