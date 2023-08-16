package com.onlineshop.dto;

import com.onlineshop.domain.Order;
import com.onlineshop.domain.OrderState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.time.OffsetDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Integer orderId;
    private CustomerDTO customer;
    private ShopDTO shop;
    private OffsetDateTime orderDate;
    private OrderState state;
    private ResponseEntity<OrderDTO> status;

    public static OrderDTO getInstance(Order order) {
        return new OrderDTO(order.getOrderId(),
                CustomerDTO.getInstance(order.getCustomer()),
                ShopDTO.getInstance(order.getShop()),
                order.getOrderDate(),
                order.getState(),
                ResponseEntity.ok().build());
    }

    public void setStatus(ResponseEntity<OrderDTO> status) {
        this.status = status;
    }
}
