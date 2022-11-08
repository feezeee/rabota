package com.java.rabota.web.models.request.category_controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCategoryRequest {
    @JsonProperty("name")
    private String name;
}
