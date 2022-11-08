package com.java.rabota.bll.services.abstractions;

import com.java.rabota.bll.models.basket_service.GetBasketOutput;

import javax.naming.AuthenticationException;

public interface BasketService {
    GetBasketOutput getBasket(int userId);
    void addUpdateBook(int userId, int bookId, int count) throws AuthenticationException;
    void deleteBookFromBasket(int userId, int bookId) throws AuthenticationException;
}
