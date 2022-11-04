package com.java.rabota.dal.entities;

import lombok.Getter;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Service
@Getter
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_role_id", nullable=false)
    private UserRoleEntity userRole;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<OrderEntity> orders = new ArrayList<>();
}
