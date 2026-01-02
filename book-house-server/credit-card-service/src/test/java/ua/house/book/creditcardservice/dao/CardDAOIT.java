package ua.house.book.creditcardservice.dao;

import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ua.house.book.creditcardservice.config.TestCreditCardConfig;
import ua.house.book.creditcardservice.config.TestHibernateConfig;
import ua.house.book.creditcardservice.domain.Currency;
import ua.house.book.creditcardservice.domain.dto.response.AccountDto;
import ua.house.book.creditcardservice.domain.entity.Card;
import ua.house.book.creditcardservice.domain.entity.Money;
import ua.house.book.creditcardservice.domain.entity.MoneyCards;
import ua.house.book.creditcardservice.exception.CardNotFoundException;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestHibernateConfig.class, TestCreditCardConfig.class})
class CardDAOIT {
    @Inject
    private CardDAO cardDAO;
    private AccountDto userAccountDto;
    private Card card;

    @BeforeEach
    void setUp() {
        userAccountDto = AccountDto.builder().id(1L).email("dimas@gmail.com").build();
        card = Card.builder()
                   .numberCard("xxxx-xxxx-xxxx-xxxx")
                   .cardEndDataMonth((short) 5)
                   .cardEndDataYear((short) 2026)
                   .cvc2("859")
                   .moneyCards(MoneyCards.builder()
                                         .spendLimit(1000)
                                         .money(Money.builder()
                                                     .id(null)
                                                     .amount(2200)
                                                     .currency(Currency.UAH)
                                                     .build())
                                         .build())
                   .accountId(userAccountDto.id())
                   .build();
    }

    @Test
    void saveCard() {
        cardDAO.saveCard(card);
        Card cardFound = cardDAO.getCard(userAccountDto.id())
                                .orElseThrow(() -> new CardNotFoundException("Not found card with this user id: "
                                        + userAccountDto.id()));
        System.out.println(cardFound);
        Assertions.assertEquals(card, cardFound);

    }
}