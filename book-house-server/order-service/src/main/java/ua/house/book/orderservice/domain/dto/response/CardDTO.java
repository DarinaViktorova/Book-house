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
public class CardDTO {
    private Long id;
    @JsonProperty("number_card")
    private String numberCard;
    @JsonProperty("card_end_data_month")
    private Short cardEndDataMonth;
    @JsonProperty("card_end_data_year")
    private Short cardEndDataYear;
    private String cvc2;
    private MoneyCardDTO moneyCard;
    @JsonProperty("account_email")
    private String accountEmail;
}
