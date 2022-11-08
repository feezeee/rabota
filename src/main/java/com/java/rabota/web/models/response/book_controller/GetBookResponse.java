package com.java.rabota.web.models.response.book_controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GetBookResponse {
    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("price")
    private double price;
    @JsonProperty("count")
    private int count;
    @JsonProperty("categories")
    private List<CategoryModelForGetBookResponse> categories = new ArrayList<>();
}
