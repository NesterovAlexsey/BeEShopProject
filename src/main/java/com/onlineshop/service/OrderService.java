package com.onlineshop.service;

import com.onlineshop.dto.OrderDTO;
import com.onlineshop.domain.*;
import com.onlineshop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ShopRepository shopRepository;

    public OrderDTO createOrder(Integer customerId, Integer shopId, Integer productId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isEmpty()) {
            OrderDTO incorrectCustomerId = new OrderDTO();
            incorrectCustomerId.setStatus(ResponseEntity.notFound()
                    .header("Warning", "customer Id " + customerId + " not Found")
                    .build());
            return incorrectCustomerId;
        }

        Optional<Shop> shop = shopRepository.findById(shopId);
        if (shop.isEmpty()) {
            OrderDTO incorrectShopId = new OrderDTO();
            incorrectShopId.setStatus(ResponseEntity.notFound()
                    .header("Warning", "Shop Id " + shopId + " not Found")
                    .build());
            return incorrectShopId;
        }

        Optional<Product> product = productRepository.findById(productId);
        if (product.isEmpty()) {
            OrderDTO incorrectProductId = new OrderDTO();
            incorrectProductId.setStatus(ResponseEntity.notFound()
                    .header("Warning", "Product Id " + productId + " not Found")
                    .build());
            return incorrectProductId;
        }

        Order order = new Order();
        order.setCustomer(customer.get());
        order.setShop(shop.get());
        order.setOrderDate(OffsetDateTime.now());
        order.setState(OrderState.NEW);
        order = orderRepository.save(order);

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrder(order);
        orderDetail.setProduct(product.get());
        orderDetail.setQuantity(1);
        orderDetailRepository.save(orderDetail);

        OrderDTO result = OrderDTO.getInstance(order);
        result.setStatus(ResponseEntity.ok().build());
        return result;
    }

    public OrderDTO addProduct(Integer orderId, Integer productId) {
        Optional<Order> order = orderRepository.findById(orderId);
        Optional<Product> product = productRepository.findById(productId);
        //Todo check if order state new
        //Todo check if product exists

        List<OrderDetail> orderList = orderDetailRepository.findByOrder(order.get());

        boolean isProductFound = false;

        for (OrderDetail orderDetail : orderList) {
            if (orderDetail.getProduct().equals(product.get())) {
                orderDetail.setQuantity(orderDetail.getQuantity() + 1);
                orderDetailRepository.save(orderDetail);
                isProductFound = true;
                break;
            }
        }
        if (!isProductFound) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order.get());
            orderDetail.setProduct(product.get());
            orderDetail.setQuantity(1);
            orderDetailRepository.save(orderDetail);
        }

        return OrderDTO.getInstance(order.get());
    }

    public OrderDTO deleteProduct(Integer orderId, Integer productId) {
        //Todo check if order state new
        Optional<Order> order = orderRepository.findById(orderId);
        Optional<Product> product = productRepository.findById(productId);
        //Todo check if product exists

        List<OrderDetail> orderList = orderDetailRepository.findByOrder(order.get());

        boolean isProductFound = false;

        for (OrderDetail orderDetail : orderList) {
            if (orderDetail.getProduct().equals(product.get())) {
                //Todo check quantity
                orderDetail.setQuantity(orderDetail.getQuantity() - 1);
                orderDetailRepository.save(orderDetail);
                isProductFound = true;
                break;
            }
        }
        if (!isProductFound) {
            //Todo error log
        }

        return OrderDTO.getInstance(order.get());
    }

    public OrderDTO updateState(Integer orderId) {
        Optional<Order> optOrder = orderRepository.findById(orderId);
        //Todo check if order exists
        //Todo check if order state new

        Order order = optOrder.get();
        order.setState(OrderState.SENT);
        order = orderRepository.save(order);

        return OrderDTO.getInstance(order);
    }

    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        List<OrderDTO> ordersDTO = new ArrayList<>();
        orders.forEach(order -> ordersDTO.add(OrderDTO.getInstance(order)));
        return ordersDTO;
    }
}
