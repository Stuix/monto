package com.pvtgrupp8.monto;


import com.pvtgrupp8.monto.dao.AttractionRepository;
import com.pvtgrupp8.monto.entities.Attraction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AttractionTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AttractionRepository attractionRepository;


    @Test
    public void testEmptyRepository(){
        List<Attraction> attractions = attractionRepository.findAll();
        assertTrue(attractions.isEmpty());
    }

    @Test
    public void testAddToRepository(){
        List<Attraction> attractions = new ArrayList<>();
        for (int i = 0; i < 20 ; i++) {
          Attraction x = new Attraction();
          x.setTitle(""+i);
          x.setTitleEnglish(i+"eng");
          x.setYearMade(i*i);
          x.setDescription("Lorem Ipsum"+i);
          x.setPicture("The picture"+i);
          attractions.add(x);
          attractionRepository.save(x);
        }

        for (int i = 0; i < 20; i++) {
            Attraction temp = attractions.get(i);
            Attraction repoTemp = attractionRepository.findById(temp.getId());
            assertSame(temp,repoTemp);
            assertEquals(temp.getYearMade(),repoTemp.getYearMade());
            assertEquals(temp.getTitle(),repoTemp.getTitle());
        }



    }


}
