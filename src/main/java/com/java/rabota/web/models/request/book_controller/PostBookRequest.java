package com.java.rabota.web.models.request.book_controller;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class PostBookRequest {
    @JsonProperty("name")
    public String name;
    @JsonProperty("description")
    public String description;
    @JsonProperty("price")
    public double price;
    @JsonProperty("count")
    public int count;
    @JsonProperty("categories")
    public List<CategoryModelForPostBookRequest> categories = new ArrayList<>();
}
