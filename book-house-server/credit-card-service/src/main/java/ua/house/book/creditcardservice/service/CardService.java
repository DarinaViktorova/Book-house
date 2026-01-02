package ua.house.book.creditcardservice.service;

import org.springframework.security.core.Authentication;
import ua.house.book.creditcardservice.domain.dto.request.CardDTO;

public interface CardService {

    CardDTO readCard(CardDTO cardDTO);
    void createCard(CardDTO cardDTO);
    CardDTO readCardByAccountId(Long accountId);
    void updateCard(CardDTO cardDTO);
}
