package ua.house.book.productservice.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class ProductDTO {
    @EqualsAndHashCode.Exclude
    private Long id;
    @JsonProperty("product_name")
    private String productName;
    @JsonProperty("available_count_products")
    private Integer availableCountProducts;
    private MoneyDTO moneyDTO;
}
