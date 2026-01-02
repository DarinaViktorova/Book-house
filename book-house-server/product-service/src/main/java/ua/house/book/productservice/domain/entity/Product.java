package ua.house.book.productservice.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "products")
@EqualsAndHashCode
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "available_count_products")
    private Integer availableCountProducts;

    @EqualsAndHashCode.Exclude
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "money_id", referencedColumnName = "id")
    private Money money;
}
