package com.java.rabota.dal.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;

@Data @AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class OrderIdEntity implements Serializable {
    @Column(name = "book_id")
    private int bookId;

    @Column(name = "user_id")
    private int userId;
}
