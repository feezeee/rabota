package com.java.rabota.bll.models.book_service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CreateBookInput {
    private String name;
    private String description;
    private double price;
    private int count;
    private List<CategoryModelForCreateBookInput> categories = new ArrayList<>();
}
