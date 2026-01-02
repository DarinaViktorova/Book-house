package ua.house.book.creditcardservice.dao;

import ua.house.book.creditcardservice.domain.entity.Card;

import java.util.Optional;

public interface CardDAO {
    void saveCard(Card card);
    Optional<Card> getCard(Long idAccount);
}
