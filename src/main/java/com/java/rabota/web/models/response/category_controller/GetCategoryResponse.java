package com.java.rabota.web.models.response.category_controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.SequenceGenerator;

@Getter
@Setter
public class GetCategoryResponse {
    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("book_count")
    private int bookCount;
}
