package com.java.rabota.web.models.request.book_controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryModelForPutBookRequest {
    @JsonProperty("id")
    private int id;
}
