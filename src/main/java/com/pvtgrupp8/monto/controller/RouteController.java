package com.pvtgrupp8.monto.controller;

import com.pvtgrupp8.monto.dao.RouteRepository;
import com.pvtgrupp8.monto.entities.Route;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/routes-with-meta")
@CrossOrigin("http://localhost:8100")
public class RouteController {

    private RouteRepository routeRepository;

    @Autowired
    public RouteController(RouteRepository rr) { routeRepository = rr; }

    @GetMapping("/{routeId}") // When using pathvariable the mapping MUST mach the @PathVariable
    public Route getRoute(@PathVariable("routeId") int id) {
        return routeRepository.findById(id);
    }

    @GetMapping("") // When using pathvariable the mapping MUST mach the @PathVariable
    public List<Route> getRoutes(){
        return routeRepository.findAll();
    }

}
