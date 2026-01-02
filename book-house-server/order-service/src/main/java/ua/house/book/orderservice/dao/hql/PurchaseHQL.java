package ua.house.book.orderservice.dao.hql;

public class PurchaseHQL {
    public static final String GET_ALL_PURCHASE_BY_ACCOUNT_ID =
            """
                    From Purchase p where p.order.accountId = :idAccount
                    """;

    public static final String GET_ALL_ORDER_BY_ACCOUNT_ID =
            """
                    From Order o where o.accountId = :idAccount
                    """;
    private PurchaseHQL(){

    }
}
