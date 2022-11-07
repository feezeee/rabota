package com.java.rabota.bll.models.basket_service;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class GetBasket {
    private ArrayList<BookModelForGetBasket> books = new ArrayList<>();
}
