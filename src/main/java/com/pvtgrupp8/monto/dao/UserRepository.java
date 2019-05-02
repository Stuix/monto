package com.pvtgrupp8.monto.dao;

import com.pvtgrupp8.monto.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
