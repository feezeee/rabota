package com.java.rabota.web.controllers;

import com.java.rabota.bll.models.user_service.CreateUser;
import com.java.rabota.bll.services.abstractions.UserService;
import com.java.rabota.web.models.request.auth_controller.PostRegistrationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String getLoginPage()
    {
        return "login";
    }

    @RequestMapping("/registration")
    public String getRegistrationPage(){
        return "registration";
    }

    @PostMapping("/registration")
    public ResponseEntity registrate(@RequestBody PostRegistrationModel registrationModel){
        try{
            var checkLogin = userService.getUserByLogin(registrationModel.getLogin());
            if(!checkLogin.isEmpty())
            {
                return ResponseEntity.status(400).build();
            }
            var createUser = new CreateUser();
            createUser.setFirstName(registrationModel.getFirstName());
            createUser.setLastName(registrationModel.getLastName());
            createUser.setPhoneNumber(registrationModel.getPhoneNumber());
            createUser.setLogin(registrationModel.getLogin());
            createUser.setPassword(registrationModel.getPassword());
            userService.registrateUser(createUser);
            return ResponseEntity.status(200).build();
        }
        catch (Exception ex){
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/check-login")
    public ResponseEntity isExist(@RequestParam String login){
        try {
            var checkLogin = userService.getUserByLogin(login);
            if(checkLogin.isEmpty())
            {
                return ResponseEntity.status(400).build();
            }
            return ResponseEntity.ok().build();
        }
        catch (Exception ex)
        {
            return ResponseEntity.status(500).build();
        }
    }

}
