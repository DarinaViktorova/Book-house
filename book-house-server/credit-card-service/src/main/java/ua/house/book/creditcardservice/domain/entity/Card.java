package ua.house.book.creditcardservice.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "cards")
@EqualsAndHashCode
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;
    @Column(name = "number_card", unique = true)
    private String numberCard;
    @Column(name = "card_end_data_month")
    private Short cardEndDataMonth;
    @Column(name = "card_end_data_year")
    private Short cardEndDataYear;
    private String cvc2;

    @EqualsAndHashCode.Exclude
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "money_card_id", referencedColumnName = "id")
    private MoneyCards moneyCards;

    private Long accountId;
}
