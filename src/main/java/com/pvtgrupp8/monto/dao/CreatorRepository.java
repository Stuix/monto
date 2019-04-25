package com.pvtgrupp8.monto.dao;

import com.pvtgrupp8.monto.entities.Creator;
import com.pvtgrupp8.monto.entities.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreatorRepository extends JpaRepository<Creator, Integer> {

}
