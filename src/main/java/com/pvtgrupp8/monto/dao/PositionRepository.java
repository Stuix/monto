package com.pvtgrupp8.monto.dao;

import com.pvtgrupp8.monto.entities.Attraction;
import com.pvtgrupp8.monto.entities.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Integer> {

}
