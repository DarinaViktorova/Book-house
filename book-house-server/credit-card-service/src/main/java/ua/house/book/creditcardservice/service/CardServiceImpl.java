package ua.house.book.creditcardservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import ua.house.book.creditcardservice.dao.CardDAO;
import ua.house.book.creditcardservice.domain.dto.request.CardDTO;
import ua.house.book.creditcardservice.domain.dto.request.MoneyCardDTO;
import ua.house.book.creditcardservice.domain.dto.request.MoneyDTO;
import ua.house.book.creditcardservice.domain.dto.response.AccountDto;
import ua.house.book.creditcardservice.domain.entity.Card;
import ua.house.book.creditcardservice.domain.entity.Money;
import ua.house.book.creditcardservice.domain.entity.MoneyCards;
import ua.house.book.creditcardservice.exception.CardNotFoundException;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CardServiceImpl implements CardService {
    private final CardDAO cardDAO;
    private final RestTemplate restTemplate;

    private Optional<AccountDto> findAccount(String userEmail) {
        return Optional.ofNullable(restTemplate.getForEntity("http://localhost:8080/api/auth/account/" + userEmail, AccountDto.class)
                                               .getBody());
    }

    private Card initCard(CardDTO cardDTO) {
        var money = Money.builder()
                         .amount(cardDTO.getMoneyCard()
                                        .getMoneyDTO()
                                        .getAmount())
                         .currency(cardDTO.getMoneyCard()
                                          .getMoneyDTO()
                                          .getCurrency())
                         .build();
        var moneyCards = MoneyCards.builder()
                                   .money(money)
                                   .spendLimit(cardDTO.getMoneyCard()
                                                      .getSpendLimit())
                                   .build();
        Optional<AccountDto> accountDto = findAccount(cardDTO.getAccountEmail());
        if (accountDto.isEmpty()) {
            throw new IllegalStateException("Account was not found! Email: " + cardDTO.getAccountEmail());
        }
        return Card.builder()
                   .moneyCards(moneyCards)
                   .numberCard(cardDTO.getNumberCard())
                   .cardEndDataMonth(cardDTO.getCardEndDataMonth())
                   .cardEndDataYear(cardDTO.getCardEndDataYear())
                   .cvc2(cardDTO.getCvc2())
                   .accountId(accountDto.get()
                                        .id())
                   .build();
    }

    @Transactional
    @Override
    public void createCard(CardDTO cardDTO) {
        Card card = initCard(cardDTO);
        cardDAO.saveCard(card);
    }

    @Override
    public CardDTO readCardByAccountId(Long accountId) {
        Card card = cardDAO.getCard(accountId)
                      .orElseThrow(() -> new CardNotFoundException("Not found user`s card by id user: "
                              + accountId));
        return CardDTO
                .builder()
                .id(card.getId())
                .numberCard(card.getNumberCard())
                .moneyCard(MoneyCardDTO.builder()
                                       .spendLimit(card.getMoneyCards()
                                                       .getSpendLimit())
                                       .moneyDTO(MoneyDTO.builder()
                                                         .amount(card.getMoneyCards()
                                                                     .getMoney()
                                                                     .getAmount())
                                                         .currency(card.getMoneyCards()
                                                                       .getMoney()
                                                                       .getCurrency())
                                                         .build())
                                       .build())
                .cardEndDataMonth(card.getCardEndDataMonth())
                .cardEndDataYear(card.getCardEndDataMonth())
                .cvc2(card.getCvc2())
                .build();
    }

    @Transactional
    @Override
    public void updateCard(CardDTO cardDTO) {
        createCard(cardDTO);
    }

    @Override
    public CardDTO readCard(CardDTO cardDTO) {
        Optional<AccountDto> accountDto = findAccount(cardDTO.getAccountEmail());
        if (accountDto.isEmpty()) {
            throw new IllegalStateException("Account was not found! Email: " + cardDTO.getAccountEmail());
        }
        Card card = cardDAO.getCard(accountDto.get().id())
                           .orElseThrow(() -> new CardNotFoundException("Not found card with this numberCard: "
                                   + cardDTO.getNumberCard()));
        return CardDTO
                .builder()
                .numberCard(card.getNumberCard())
                .moneyCard(MoneyCardDTO.builder()
                                       .spendLimit(card.getMoneyCards()
                                                       .getSpendLimit())
                                       .moneyDTO(MoneyDTO.builder()
                                                         .amount(card.getMoneyCards()
                                                                     .getMoney()
                                                                     .getAmount())
                                                         .currency(card.getMoneyCards()
                                                                       .getMoney()
                                                                       .getCurrency())
                                                         .build())
                                       .build())
                .cardEndDataMonth(card.getCardEndDataMonth())
                .cardEndDataYear(card.getCardEndDataMonth())
                .cvc2(card.getCvc2())
                .build();
    }
}
