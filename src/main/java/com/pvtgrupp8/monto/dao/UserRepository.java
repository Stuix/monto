package com.pvtgrupp8.monto.dao;

import com.pvtgrupp8.monto.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

    @Override
    List<User> findAll();


}
