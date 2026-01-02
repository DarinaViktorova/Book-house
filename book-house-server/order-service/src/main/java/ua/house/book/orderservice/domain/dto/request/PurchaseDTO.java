package ua.house.book.orderservice.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ua.house.book.orderservice.domain.dto.response.MoneyDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class PurchaseDTO {
    @JsonProperty("purchase_name")
    private String purchaseName;
    @JsonProperty("count_purchases")
    private Integer countPurchases;
    private MoneyDTO moneyDTO;
}
