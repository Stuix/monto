package com.pvtgrupp8.monto.controller;


import com.pvtgrupp8.monto.dao.AttractionRepository;
import com.pvtgrupp8.monto.entities.Attraction;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attractions-with-meta")
public class AttractionController {

    private AttractionRepository attractionRepository;

    @Autowired
    public AttractionController(AttractionRepository ar) {
        attractionRepository = ar;
    }


    @GetMapping("/{attractionId}") // When using pathvariable the mapping MUST mach the @PathVariable
    public Attraction getAttraction(@PathVariable("attractionId") int id){
        return attractionRepository.findById(id);
    }

    @GetMapping("") // When using pathvariable the mapping MUST mach the @PathVariable
    public List<Attraction> getAttractions(){
        return attractionRepository.findAllByCategory_Name("Statue");
    }

    @GetMapping("/findByTitleAndCategory/{category}/{title}")
    public List<Attraction> findByTitleAndCategory(
        @PathVariable("category") String category,
        @PathVariable("title") String title){
        List <Attraction> attractions = attractionRepository.findByTitleIgnoreCaseContainingAndCategory_Name(title,category);
        if(attractions == null || attractions.size() == 0){
            throw new ResourceNotFoundException("No Attraction with that name");
        }
        return attractions;
    }

}
