package com.onlineshop.domain;

public enum OrderState {
    NEW,
    WAITING_PAYMENT,
    WAITING_DELIVERY,
    DELIVERED,
    CANCELED,
}
