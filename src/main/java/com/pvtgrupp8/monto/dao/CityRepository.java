package com.pvtgrupp8.monto.dao;

import com.pvtgrupp8.monto.entities.City;
import com.pvtgrupp8.monto.entities.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Integer> {

}
