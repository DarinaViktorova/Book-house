package ua.house.book.orderservice.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import ua.house.book.orderservice.domain.Currency;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "money")
@EqualsAndHashCode
public class Money {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;
    private Integer amount;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    @EqualsAndHashCode.Exclude
    @OneToOne(mappedBy = "money", cascade = CascadeType.ALL)
    private Purchase purchase;
}
