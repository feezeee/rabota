package com.java.rabota.bll.models.user_service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUser {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String login;
    private String password;
}
