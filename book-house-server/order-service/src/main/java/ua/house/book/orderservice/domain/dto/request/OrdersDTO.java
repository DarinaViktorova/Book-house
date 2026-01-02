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
public class OrdersDTO {
    List<OrderDTO> orderDTOList;
    @JsonProperty("account_email")
    private String accountEmail;
}
