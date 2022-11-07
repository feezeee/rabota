package com.java.rabota.bll.services.abstractions;

import com.java.rabota.bll.models.user_service.CreateUser;
import com.java.rabota.bll.models.user_service.GetUser;

import java.util.Optional;

public interface UserService {
    Optional<GetUser> getUserByLogin(String login);
    void registrateUser(CreateUser createUser) throws Exception;
}
