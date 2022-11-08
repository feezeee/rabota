package com.java.rabota.bll.models.category_service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetCategoryOutput {
    private int id;
    private String name;
    private int bookCount;
}
