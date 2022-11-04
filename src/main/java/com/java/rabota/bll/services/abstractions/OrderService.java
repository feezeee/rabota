package com.java.rabota.bll.services.abstractions;

import com.java.rabota.bll.models.order_service.GetOrder;

import java.util.Optional;

public interface OrderService {
    public Optional<GetOrder> getOrder(int userId);
}
