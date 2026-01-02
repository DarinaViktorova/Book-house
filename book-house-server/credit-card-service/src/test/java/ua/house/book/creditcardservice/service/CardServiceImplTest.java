package ua.house.book.creditcardservice.service;

import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;
import ua.house.book.creditcardservice.config.TestCreditCardConfig;
import ua.house.book.creditcardservice.config.TestHibernateConfig;
import ua.house.book.creditcardservice.dao.CardDAO;
import ua.house.book.creditcardservice.domain.Currency;
import ua.house.book.creditcardservice.domain.dto.request.CardDTO;
import ua.house.book.creditcardservice.domain.dto.request.MoneyCardDTO;
import ua.house.book.creditcardservice.domain.dto.request.MoneyDTO;
import ua.house.book.creditcardservice.domain.dto.response.AccountDto;
import ua.house.book.creditcardservice.domain.entity.Card;
import ua.house.book.creditcardservice.domain.entity.Money;
import ua.house.book.creditcardservice.domain.entity.MoneyCards;


import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@ContextConfiguration(classes = {TestHibernateConfig.class, TestCreditCardConfig.class})
class CardServiceImplTest {
    @InjectMocks
    private CardServiceImpl cardService;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private CardDAO cardDAO;
    private AccountDto userAccountDto;

    @BeforeEach
    void setUp() {
        userAccountDto = AccountDto.builder()
                .id(1L)
                .email("dimas@gmail.com")
                .build();
    }

    @Test
    void createCard() {
        CardDTO cardDTO = CardDTO.builder()
                .numberCard("xxxx-xxxx-xxxx-xxxx")
                .cardEndDataMonth((short) 5)
                .cardEndDataYear((short) 2026)
                .cvc2("859")
                .accountEmail(userAccountDto.email())
                .moneyCard(MoneyCardDTO.builder()
                        .spendLimit(1000)
                        .moneyDTO(MoneyDTO.builder()
                                .amount(2200)
                                .currency(Currency.UAH)
                                .build())
                        .build())
                .build();
        Mockito.doNothing()
                .when(cardDAO)
                .saveCard(any());

        ResponseEntity<AccountDto> responseEntity = new ResponseEntity<>(userAccountDto, HttpStatus.OK);

        when(restTemplate.getForEntity(
                "http://localhost:8080/api/auth/account/" + userAccountDto.email(), AccountDto.class))
                .thenReturn(responseEntity);

        cardService.createCard(cardDTO);

        Mockito.verify(cardDAO, Mockito.times(1))
                .saveCard(Card.builder()
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
                        .build());
    }

}