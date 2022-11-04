package com.java.rabota.bll.models.order_service;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class GetOrder {
    private int userId;
    private ArrayList<BookModelForGetOrder> books = new ArrayList<>();
}
