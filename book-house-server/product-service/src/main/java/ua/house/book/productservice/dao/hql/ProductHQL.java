package ua.house.book.productservice.dao.hql;

public class ProductHQL {
    public static final String GET_ALL_PRODUCTS =
            """
                       FROM Product
                    """;
    public static final String GET_ALL_PRODUCTS_ASC =
            """
                       FROM Product p order by p.productName asc
                    """;
    ;
    public static final String GET_ALL_PRODUCTS_DESC =
            """
                       FROM Product p order by p.productName desc
                    """;
    public static final String DELETE_ALL_PRODUCTS =
            """
                    DELETE FROM Product
                    """;

    private ProductHQL() {
    }


}
