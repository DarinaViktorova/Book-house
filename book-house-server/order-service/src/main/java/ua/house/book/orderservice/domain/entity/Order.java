package ua.house.book.orderservice.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders")
@EqualsAndHashCode
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;

    @Column(name = "order_date")
    private String orderDate;

    @Column(name = "order_time")
    private String orderTime;
    private Long accountId;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Purchase> purchaseList;
}
