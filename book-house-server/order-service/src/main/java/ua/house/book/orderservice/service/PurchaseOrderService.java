package ua.house.book.orderservice.service;

import org.springframework.security.core.Authentication;
import ua.house.book.orderservice.domain.dto.request.OrdersDTO;

public interface PurchaseOrderService {
    void createListOrder(OrdersDTO ordersDTO);
}
