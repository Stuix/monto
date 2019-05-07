package com.pvtgrupp8.monto.dao;

import com.pvtgrupp8.monto.entities.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteRepository extends JpaRepository<Route, Integer> {

    List<Route> findAllByAttractions(String attractionName);

    List<Route>findByRouteNameIgnoreCaseContaining(String routeName);


}
