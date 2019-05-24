package com.pvtgrupp8.monto.controller;

import com.pvtgrupp8.monto.dao.UserRepository;
import com.pvtgrupp8.monto.entities.Route;
import com.pvtgrupp8.monto.entities.User;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/controllers/users")
public class UserController {

    // This is needed To handle the Annotation constraints on the attributes in the classes,
    // and produce a 400 instead of 500
    /*@ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }*/

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository ur){
       userRepository=ur;
    }


    @PostMapping(path="/create")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User x){
        return userRepository.save(x);

    }

    @GetMapping(value="/all")
    public List<User> getAll(){
        return userRepository.findAll();
    }

    @GetMapping(value="/{email}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserByEmail(@PathVariable("email") String email){
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }
        return user;
    }

    @GetMapping(value="/{userId}/activeRoute")
    public User getActiveRoute(@PathVariable("userId") int id) {
        return userRepository.findById(id);
    }

    @DeleteMapping(path="/delete-user-by-email/{email}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserByEmail(@PathVariable("email") String email){
        userRepository.deleteByEmail(email);
    }



}
