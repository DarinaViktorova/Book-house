package ua.house.book.orderservice.domain.dto.response;

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
