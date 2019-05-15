package com.pvtgrupp8.monto.dao;

import com.pvtgrupp8.monto.entities.Creator;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = {"http://localhost:8100","http://10.200.31.137:8100","http://localhost:8000" })
public interface CreatorRepository extends JpaRepository<Creator, Integer> {


    List<Creator> findByLastNameOrFirstNameIgnoreCaseContaining(String lastName,String firstName);


}
