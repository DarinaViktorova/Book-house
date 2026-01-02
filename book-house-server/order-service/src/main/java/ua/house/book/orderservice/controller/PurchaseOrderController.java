package ua.house.book.orderservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ua.house.book.orderservice.domain.dto.request.OrdersDTO;
import ua.house.book.orderservice.service.PurchaseOrderService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/core/orders")
public class PurchaseOrderController {
    private final PurchaseOrderService purchaseOrderService;

    @PostMapping("/create-order")
    public ResponseEntity<String> createListOrder(@RequestBody OrdersDTO ordersDTO) {
        purchaseOrderService.createListOrder(ordersDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Order " + ordersDTO + " is successful");
    }

}

