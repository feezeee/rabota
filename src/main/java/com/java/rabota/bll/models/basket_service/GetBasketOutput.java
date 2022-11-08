package com.java.rabota.bll.models.basket_service;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class GetBasketOutput {
    private ArrayList<BookModelForGetBasketOutput> books = new ArrayList<>();
}
