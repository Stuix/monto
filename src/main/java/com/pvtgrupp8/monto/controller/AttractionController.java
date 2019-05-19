package com.pvtgrupp8.monto.controller;


import com.pvtgrupp8.monto.dao.AttractionRepository;
import com.pvtgrupp8.monto.entities.Attraction;
import java.util.List;

import com.pvtgrupp8.monto.entities.Position;
import com.pvtgrupp8.monto.entities.Route;
import com.pvtgrupp8.monto.entities.User;
import com.pvtgrupp8.monto.wrappers.CreateUserSpotWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.UriToEntityConverter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attractions-with-meta")
@CrossOrigin("http://localhost:8100")
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

    @GetMapping("") // When using pathvariable the mapping MUST mach the @PathVariable
    public List<Attraction> getAttractions(){
        return attractionRepository.findAllByCategory_Name("Statue");
    }

    @RequestMapping(path="/addUserSpot", method=RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.CREATED)
    public Attraction adduserSpot(@RequestBody CreateUserSpotWrapper cus){
        Attraction userSpot = cus.getUserSpot();
        Position pos = cus.getPosition();

        userSpot.setPosition(pos);
        attractionRepository.save(userSpot);

        return userSpot;

    }

}
