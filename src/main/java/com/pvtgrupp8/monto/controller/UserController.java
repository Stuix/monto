package com.pvtgrupp8.monto.controller;

import com.pvtgrupp8.monto.dao.UserRepository;
import com.pvtgrupp8.monto.entities.Route;
import com.pvtgrupp8.monto.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/controllers/users")
public class UserController {


    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository ur){
       userRepository=ur;
    }

    @GetMapping(value="/all")
    public List<User> getAll(){
        return userRepository.findAll();
    }

    @GetMapping(value="/{email}")
    public User getUserByEmail(@PathVariable("email") String email){
        return userRepository.findByEmail(email);
    }

    @GetMapping(value="/{userId}/activeRoute")
    public User getActiveRoute(@PathVariable("userId") int id) {
        return userRepository.findById(id);
    }

}
