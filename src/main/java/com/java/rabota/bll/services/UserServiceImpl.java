package com.java.rabota.bll.services;

import com.java.rabota.bll.models.user_service.CreateUserInput;
import com.java.rabota.bll.models.user_service.GetUserOutput;
import com.java.rabota.bll.repositories.abstractions.UserRepository;
import com.java.rabota.bll.repositories.abstractions.UserRoleRepository;
import com.java.rabota.bll.services.abstractions.UserService;
import com.java.rabota.dal.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public Optional<GetUserOutput> getUserByLogin(String login){
        var user = userRepository.findByLogin(login);
        if(user.isEmpty())
            return Optional.empty();
        var userEntity = user.get();
        var mappedUser = new GetUserOutput();
        mappedUser.setId(userEntity.getId());
        mappedUser.setLogin(userEntity.getLogin());
        mappedUser.setPassword(userEntity.getPassword());
        mappedUser.setFirstName(userEntity.getFirstName());
        mappedUser.setLastName(userEntity.getLastName());
        mappedUser.setPhoneNumber(userEntity.getPhoneNumber());
        return Optional.of(mappedUser);
    }
    
    @Override
    public void registrateUser(CreateUserInput createUser) throws Exception {

        var user = new UserEntity();
        user.setFirstName(createUser.getFirstName());
        user.setLastName(createUser.getLastName());
        user.setPhoneNumber(createUser.getPhoneNumber());
        user.setLogin(createUser.getLogin());
        user.setPassword(new BCryptPasswordEncoder().encode(createUser.getPassword()));

        var existRole = userRoleRepository.findByName("USER");
        if(existRole.isEmpty())
        {
            throw new Exception();
        }

        user.setUserRole(existRole.get());
        userRepository.save(user);
    }
}
