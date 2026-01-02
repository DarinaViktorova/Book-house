package ua.house.book.productservice.dao.hql;

public class OrderHQL {
    public static final String GET_ALL_ORDER_BY_ACCOUNT_ID =
            """
                    SELECT o FROM Order o where o.account.id = :idAccount
                    """;

    private OrderHQL(){}
}
