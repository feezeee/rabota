package com.java.rabota.bll.models.basket_service;

import com.java.rabota.dal.entities.CategoryEntity;
import com.java.rabota.dal.entities.OrderEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BookModelForGetBasket {
    private int id;
    private String name;
    private String description;
    private double price;
    private int availableCount;
    private int countInBasket;
}
