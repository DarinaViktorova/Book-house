package ua.book.house.auth.dao.hql;

public class AccountHQL {
    private AccountHQL() {
    }

    public static final String FIND_USER_BY_EMAIL_AND_PASSWORD =
            """
                       SELECT distinct a FROM Account a
                            left join fetch a.roles
                            left join fetch a.user
                            left join fetch a.tokenList
                            WHERE a.email = :email AND a.password = :password
                    """;

    public static final String FIND_ADMIN_BY_EMAIL_AND_PASSWORD =
            """
                       SELECT distinct a FROM Account a
                            left join fetch a.roles
                            left join fetch a.admin
                            left join fetch a.tokenList
                            WHERE a.email = :email AND a.password = :password
                    """;

    public static final String FIND_ORDERS_FOR_ACCOUNT =
            """
                    SELECT distinct a FROM Account a
                            left join fetch a.orderList
                            WHERE a in :account
                    """;

    public static final String FIND_USER_BY_EMAIL =
            """
                       SELECT distinct a FROM Account a
                             WHERE a.email = :email
                    """;

}
