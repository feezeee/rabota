package com.java.rabota.web.models.response.book_controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryModelForGetBookResponse {
    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
}
