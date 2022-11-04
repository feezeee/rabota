package com.java.rabota.dal.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "books")
public class BookEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private double price;
    @Column(name = "count")
    private int count;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
    public List<OrderEntity> orders = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    private List<CategoryEntity> categories = new ArrayList<>();
}
