package ua.house.book.orderservice.exception;

public class OnCardHaveNotEnoughMoney extends RuntimeException{
    public OnCardHaveNotEnoughMoney() {
        super();
    }

    public OnCardHaveNotEnoughMoney(String message) {
        super(message);
    }

    public OnCardHaveNotEnoughMoney(String message, Throwable cause) {
        super(message, cause);
    }

    public OnCardHaveNotEnoughMoney(Throwable cause) {
        super(cause);
    }

    protected OnCardHaveNotEnoughMoney(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
