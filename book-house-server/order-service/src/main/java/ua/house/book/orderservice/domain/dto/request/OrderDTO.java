package ua.house.book.orderservice.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class OrderDTO {
    private List<PurchaseDTO> purchaseList;
    @JsonProperty("order_date")
    private String orderDate;
    @JsonProperty("order_time")
    private String orderTime;
}
