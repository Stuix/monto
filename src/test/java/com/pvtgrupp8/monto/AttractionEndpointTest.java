package com.pvtgrupp8.monto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.pvtgrupp8.monto.dao.AttractionRepository;
import com.pvtgrupp8.monto.dao.CategoryRepository;
import com.pvtgrupp8.monto.entities.Attraction;
import com.pvtgrupp8.monto.entities.Category;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AttractionEndpointTest {

    private static final String BASE_PATH = "/attractions-with-meta";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    AttractionRepository attractionRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    private MockMvc mvc;

    private ObjectMapper mapper = new ObjectMapper();

    private TestUtility testUtility= new TestUtility(mapper);

    private Category category;

    @Before
    public void initTests() {
        if(attractionRepository.findAll().size()!=0){
            attractionRepository.deleteAll();
        }
        Category category = new Category("Statue");
        this.category = category;
        categoryRepository.save(category);

        Attraction attraction = new Attraction();
        attraction.setTitle("The First Attraction");
        attraction.setCategory(category);
        attractionRepository.save(attraction);
    }

    @Test
    public void contextLoads() {
        assertThat(jdbcTemplate).isNotNull();
        assertThat(mvc).isNotNull();
    }

    @Test
    public void shouldStartWithOneAttraction() throws Exception{
        invokeGetAllStatueAttractions()
            .andExpect(status().isOk())
            .andExpect(jsonPath("$",hasSize(1)))
            .andExpect(jsonPath("$[0].title",is("The First Attraction")))
            .andReturn();
    }

    @Test
    public void shouldFindStatueByName() throws Exception {
            String [] attractions = new String [] {"Hej Statue","Då Statue","Hej stasd",
                "Hej Sculpture","Då Sculpture","Hej skafak"
            };

        for (String s: attractions) {
            Attraction attraction = new Attraction();
            attraction.setCategory(category);
            attraction.setTitle(s);
            invokeCreateAttraction(testUtility.toJson(attraction))
                .andExpect(status().isCreated());
            attractionRepository.save(attraction);
        }
        invokeGetAllStatueAttractions()
        .andExpect(jsonPath("$",hasSize(7)));

        invokeFindStatueByCategoryAndTitle("Statue","statue")
            .andExpect(status().isOk())
            .andExpect(jsonPath("$",hasSize(2)))
            .andExpect(jsonPath("$[*].title",
                containsInAnyOrder("Hej Statue","Då Statue")));

        invokeFindStatueByCategoryAndTitle("Statue","sculpture")
            .andExpect(status().isOk())
            .andExpect(jsonPath("$",hasSize(2)))
            .andExpect(jsonPath("$[*].title",
                containsInAnyOrder("Hej Sculpture","Då Sculpture")));
    }

   @Test
   public void shouldCreateAttraction() throws Exception{
       Attraction attraction = new Attraction();
       attraction.setTitle(testUtility.getRandomString());
       attraction.setCategory(category);
       attractionRepository.save(attraction);
       invokeCreateAttraction(testUtility.toJson(attraction))
       .andExpect(status().isCreated());

       invokeGetAllStatueAttractions()
           .andExpect(jsonPath("$",hasSize(1)));
   }

   @Test
   public void shouldDeleteAttraction() throws Exception{
        Attraction attraction = new Attraction();
        attraction.setTitle("Test");
        attraction.setCategory(category);
        attractionRepository.save(attraction);
        invokeFindStatueByCategoryAndTitle("Statue","Test")
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].title",is("Test")));
        invokeDeleteAttraction(attraction.getId())
            .andExpect(status().isNoContent());
       invokeFindStatueByCategoryAndTitle("Statue","test")
           .andExpect(status().isNotFound());
   }



    private ResultActions invokeGetAllStatueAttractions() throws  Exception{
        return mvc.perform(get(BASE_PATH)
            .accept(MediaType.APPLICATION_JSON));
    }

    private ResultActions invokeFindStatueByCategoryAndTitle(String category,String title) throws Exception {
        return mvc.perform(get(BASE_PATH+"/findByTitleAndCategory/"+category+"/"+title)
        .accept(MediaType.APPLICATION_JSON));
    }


    private ResultActions invokeCreateAttraction(byte [] attractionJson) throws Exception {
        return mvc.perform(post("/attractions")
        .content(attractionJson)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON));
    }
    private ResultActions invokeDeleteAttraction(int id) throws Exception {
        ResultActions results = mvc.perform(delete("/attractions/"+ id)
        .accept(MediaType.APPLICATION_JSON));
        return results;
    }

}
