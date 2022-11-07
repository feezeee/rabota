package com.java.rabota.bll.services;

import com.java.rabota.bll.models.user_service.GetUser;
import com.java.rabota.bll.repositories.abstractions.UserRepository;
import com.java.rabota.bll.services.abstractions.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<GetUser> getUserByLogin(String login){
        var user = userRepository.findByLogin(login);
        if(user == null)
            return Optional.empty();
        var userEntity = user.get();
        var mappedUser = new GetUser();
        mappedUser.setId(userEntity.getId());
        mappedUser.setLogin(userEntity.getLogin());
        mappedUser.setPassword(userEntity.getPassword());
        mappedUser.setFirstName(userEntity.getFirstName());
        mappedUser.setLastName(userEntity.getLastName());
        mappedUser.setPhoneNumber(userEntity.getPhoneNumber());
        return Optional.of(mappedUser);
    }
}
