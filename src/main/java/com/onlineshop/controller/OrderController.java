package com.onlineshop.controller;

import com.onlineshop.controller.dto.OrderDTO;
import com.onlineshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/create/{customerId}/{shopId}/{productId}")
    public OrderDTO createOrder(@PathVariable Integer customerId,
                                @PathVariable Integer shopId,
                                @PathVariable Integer productId) {
        return orderService.createOrder(customerId, shopId, productId);
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
