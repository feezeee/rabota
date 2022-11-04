package com.java.rabota.bll.services;


import com.java.rabota.bll.models.order_service.GetOrder;
import com.java.rabota.bll.repositories.abstractions.BookRepository;
import com.java.rabota.bll.repositories.abstractions.OrderRepository;
import com.java.rabota.bll.services.abstractions.OrderService;
import com.java.rabota.dal.entities.OrderEntity;
import com.java.rabota.dal.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Optional<GetOrder> getOrder(int userId){
        var orders = orderRepository.findByUserId(userId);

        if(orders.isEmpty()) return Optional.empty();
        var order = new GetOrder();

        return Optional.of(order);
    }
}
