package com.pvtgrupp8.monto.dao;

import com.pvtgrupp8.monto.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = {"http://localhost:8100","http://10.200.31.137:8100","http://localhost:8000" })
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

    @Override
    List<User> findAll();

    User findById(int id);

}
