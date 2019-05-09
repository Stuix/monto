package com.pvtgrupp8.monto.dao;

import com.pvtgrupp8.monto.entities.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface AttractionRepository extends JpaRepository<Attraction, Integer> {

   // @Query("from Attraction where title like %?1")
    List<Attraction> findByTitleIgnoreCaseContaining(String title);

    Attraction findById(int id);
}
