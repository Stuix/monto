package com.pvtgrupp8.monto.dao;

import com.pvtgrupp8.monto.entities.Attraction;
import com.pvtgrupp8.monto.entities.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8100")
public interface PositionRepository extends JpaRepository<Position, Integer> {

}
