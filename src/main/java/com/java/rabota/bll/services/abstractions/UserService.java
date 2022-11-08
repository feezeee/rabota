package com.java.rabota.bll.services.abstractions;

import com.java.rabota.bll.models.user_service.CreateUserInput;
import com.java.rabota.bll.models.user_service.GetUserOutput;

import java.util.Optional;

public interface UserService {
    Optional<GetUserOutput> getUserByLogin(String login);
    void registrateUser(CreateUserInput createUser) throws Exception;
}
