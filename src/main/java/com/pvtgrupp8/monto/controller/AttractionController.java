package com.pvtgrupp8.monto.controller;


import com.pvtgrupp8.monto.dao.AttractionRepository;
import com.pvtgrupp8.monto.entities.Attraction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/attraction-with-meta")
public class AttractionController {

    private AttractionRepository attractionRepository;

    @Autowired
    public AttractionController(AttractionRepository ar){
        attractionRepository = ar;
    }

    @GetMapping("/{attractionId}") // When using pathvariable the mapping MUST mach the @PathVariable
    public Attraction getAttraction(@PathVariable("attractionId") int id){
        return attractionRepository.findById(id);
    }




}
