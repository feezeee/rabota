package com.java.rabota.bll.models.book_service;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GetBookOutput {
    private int id;
    private String name;
    private String description;
    private double price;
    private int count;
    private List<CategoryModelForGetBookOutput> categories = new ArrayList<>();
}