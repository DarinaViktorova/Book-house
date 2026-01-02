package ua.house.book.creditcardservice.dao.hql;

public class CardHQL {
    private CardHQL() {
    }

    public static final String FIND_CARD_BY_CARD_NUMBER =
            """
                    From Card c where c.numberCard = :numberCard
                    """;
    public static final String FIND_CARD_BY_ACCOUNT_ID =
            """
                    From Card c where c.accountId = :idAccount
                    """;
}
