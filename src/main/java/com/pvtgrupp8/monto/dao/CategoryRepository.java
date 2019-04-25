package com.pvtgrupp8.monto.dao;

import com.pvtgrupp8.monto.entities.Category;
import com.pvtgrupp8.monto.entities.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
