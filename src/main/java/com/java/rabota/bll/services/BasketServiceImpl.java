package com.java.rabota.bll.services;


import com.java.rabota.bll.models.basket_service.BookModelForGetBasket;
import com.java.rabota.bll.models.basket_service.GetBasket;
import com.java.rabota.bll.repositories.abstractions.BookRepository;
import com.java.rabota.bll.repositories.abstractions.OrderRepository;
import com.java.rabota.bll.repositories.abstractions.UserRepository;
import com.java.rabota.bll.services.abstractions.BasketService;
import com.java.rabota.dal.entities.OrderEntity;
import com.java.rabota.dal.entities.OrderIdEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class BasketServiceImpl implements BasketService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public GetBasket getBasket(int userId) {
        var orders = orderRepository.findByUserId(userId);
        var basket = new GetBasket();
        if (orders.isEmpty()) return basket;
        for (var it :
                orders) {
            var newBook = new BookModelForGetBasket();

            var book = it.getBook();

            if (book.getCount() < it.getCount()) {
                it.setCount(book.getCount());
                orderRepository.save(it);
            }

            newBook.setId(book.getId());
            newBook.setName(book.getName());
            newBook.setDescription(book.getDescription());
            newBook.setPrice(book.getPrice());
            newBook.setCountInBasket(it.getCount());
            newBook.setAvailableCount(book.getCount());

            basket.getBooks().add(newBook);
        }

        return basket;
    }

    @Override
    public void addUpdateBook(int userId, int bookId, int count) throws AuthenticationException {

        var user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new AuthenticationException();
        }
        var book = bookRepository.findById(bookId);
        if (book.isEmpty()) {
            throw new IllegalArgumentException();
        }
        var orders = orderRepository.findByUserId(userId);

        if (book.get().getCount() < count) {
            count = book.get().getCount();
        }
        if (!orders.isEmpty()) {
            for (var order :
                    orders) {
                // Если в коллекции есть такое
                if (order.getBook().getId() == book.get().getId()) {
                    if (count <= 0) {
                        orderRepository.delete(order);
                    } else {
                        order.setCount(count);
                        orderRepository.save(order);
                    }
                    return;
                }
            }
        }
        if (count > 0) {
            var order = new OrderEntity();
            order.setCount(count);
            var id = new OrderIdEntity(book.get().getId(), user.get().getId());
            order.setId(id);
            order.setBook(book.get());
            order.setUser(user.get());
            orderRepository.save(order);
        }


    }
}

