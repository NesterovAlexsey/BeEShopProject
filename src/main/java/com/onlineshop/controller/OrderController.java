package com.onlineshop.controller;

import com.onlineshop.dto.OrderDTO;
import com.onlineshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Operation with orders:
 * - create order with 1 product
 * - added product to the order
 * - delete product from orders
 * - send order to the execution
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    //Todo - delete get endpoint before production
    @GetMapping("")
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping("/create/{customerId}/{shopId}/{productId}")
    public ResponseEntity<OrderDTO> createOrder(@PathVariable Integer customerId,
                                                @PathVariable Integer shopId,
                                                @PathVariable Integer productId) {
        OrderDTO orderDTO = orderService.createOrder(customerId, shopId, productId);
        return orderDTO.getStatus();
    }

    @PutMapping("/add/{orderId}/{productId}")
    public OrderDTO addProduct(@PathVariable Integer orderId, @PathVariable Integer productId) {
        return orderService.addProduct(orderId, productId);
    }

    @DeleteMapping("/delete/{orderId}/{productId}")
    public OrderDTO deleteProduct(@PathVariable Integer orderId, @PathVariable Integer productId) {
        return orderService.deleteProduct(orderId, productId);
    }

    @PutMapping("/updateState/{orderId}")
    public OrderDTO updateState(@PathVariable Integer orderId) {
        return orderService.updateState(orderId);
    }
}
