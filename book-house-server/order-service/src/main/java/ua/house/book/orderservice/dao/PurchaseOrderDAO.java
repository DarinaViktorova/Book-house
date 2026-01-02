package ua.house.book.orderservice.dao;

import ua.house.book.orderservice.domain.entity.Order;

import java.util.List;

public interface PurchaseOrderDAO {
    void createListOrder(List<Order> orderList);
    List<Order> getListOrder(Long idAccount);
}
