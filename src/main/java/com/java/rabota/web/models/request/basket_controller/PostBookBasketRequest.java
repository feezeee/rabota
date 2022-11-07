package com.java.rabota.web.models.request.basket_controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


public class PostBookBasketRequest {
    @JsonProperty("book_id")
    public int bookId;
    @JsonProperty("count")
    public int count;
}
