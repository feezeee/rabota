package com.java.rabota.bll.models.basket_service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookModelForGetBasketOutput {
    private int id;
    private String name;
    private String description;
    private double price;
    private int availableCount;
    private int countInBasket;
}
