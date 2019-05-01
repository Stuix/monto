package com.pvtgrupp8.monto.dao;

import com.pvtgrupp8.monto.entities.Creator;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface CreatorRepository extends JpaRepository<Creator, Integer> {


    List<Creator> findByLastNameOrFirstNameIgnoreCaseContaining(String lastName,String firstName);


}
