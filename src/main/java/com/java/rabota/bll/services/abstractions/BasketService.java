package com.java.rabota.bll.services.abstractions;

import com.java.rabota.bll.models.basket_service.GetBasket;

import javax.naming.AuthenticationException;
import java.util.Optional;

public interface BasketService {
    GetBasket getBasket(int userId);
    void addUpdateBook(int userId, int bookId, int count) throws AuthenticationException;
}
