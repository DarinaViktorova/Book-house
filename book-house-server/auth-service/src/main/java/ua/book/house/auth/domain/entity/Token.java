package ua.book.house.auth.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import ua.book.house.auth.domain.TokenType;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tokens")
@Entity
@EqualsAndHashCode
public class Token {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Exclude
    public Integer id;

    @Column(unique = true)
    public String token;

    @Enumerated(EnumType.STRING)
    @Column(name = "token_type")
    public TokenType tokenType;

    public boolean revoked;

    public boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    @EqualsAndHashCode.Exclude
    public Account account;
}