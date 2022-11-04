package com.java.rabota.dal.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "orders")
@NoArgsConstructor
public class OrderEntity {

    @EmbeddedId
    private OrderIdEntity id;

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "book_id")
    private BookEntity book;
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @Column(name = "count")
    private int count;

    public OrderEntity(BookEntity book, UserEntity user, int count) {
        this.id = new OrderIdEntity(book.getId(), user.getId());
        this.book = book;
        this.user = user;
        this.count = count;
    }
}
