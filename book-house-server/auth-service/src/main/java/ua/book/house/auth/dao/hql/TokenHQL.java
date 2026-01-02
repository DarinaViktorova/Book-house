package ua.book.house.auth.dao.hql;

public class TokenHQL {
    public static final String FIND_ALL_VALID_TOKENS_BY_ID_ACCOUNT =
            """
                    SELECT t FROM Token t WHERE t.account.id = :accountId AND (t.expired=false AND t.revoked=false)
               """;
    public static final String FIND_BY_TOKEN =
            """
                    SELECT t FROM Token t WHERE t.token = :token
               """;
    private TokenHQL(){

    }
}
