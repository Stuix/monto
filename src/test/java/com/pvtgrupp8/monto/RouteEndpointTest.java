package com.pvtgrupp8.monto;


import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.pvtgrupp8.monto.dao.RouteRepository;
import com.pvtgrupp8.monto.entities.Route;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class RouteEndpointTest {
    private static final String BASE_PATH = "/routes-with-meta";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    RouteRepository routeRepository;

    @Autowired
    private MockMvc mvc;

    private ObjectMapper mapper = new ObjectMapper();

    private TestUtility testUtility= new TestUtility(mapper);

    @Before
    public void initTests() {
        if(routeRepository.findAll().size()!=0){
            routeRepository.deleteAll();
        }
        Route route = new Route();
        route.setRouteName("The First Route");
        routeRepository.save(route);
    }

    @Test
    public void contextLoads() {
        assertThat(jdbcTemplate).isNotNull();
        assertThat(mvc).isNotNull();
    }

    @Test
    public void shouldStartWithOneRoute() throws Exception{
          MvcResult result = invokeGetAllRoutes()
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].routeName",is("The First Route")))
                .andReturn();
    }

    @Test
    public void shouldCreateRoute() throws Exception {
        Route route = new Route();
        route.setRouteName("The "+testUtility.getRandomString()+" Route");
        MvcResult results = invokeCreateRoute(testUtility.toJson(route))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.routeName",is(route.getRouteName())))
            .andReturn();
        Route routeFromRep = testUtility.fromJsonResult(results,Route.class);

        invokeFindRouteById(routeFromRep.getId())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.routeName",is(route.getRouteName())));
    }

    @Test
    public void shouldDeleteRoute() throws Exception {
        Route route = new Route();
        route.setRouteName("The "+testUtility.getRandomString()+" Route");
        MvcResult results = invokeCreateRoute(testUtility.toJson(route))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.routeName",is(route.getRouteName())))
            .andReturn();
        Route routeFromRep = testUtility.fromJsonResult(results,Route.class);

        invokeDeleteRouteById(routeFromRep.getId())
            .andExpect(status().isNoContent());

        invokeFindRouteById(routeFromRep.getId())
            .andExpect(status().isNotFound());

    }

    @Test
    public void shouldBadRequestInvalidRouteName() throws Exception{
        Route route = new Route();
        route.setRouteName("");
        invokeCreateRoute(testUtility.toJson(route))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.message[0]",is("The route needs a name!")));
        route.setRouteName(null);
        invokeCreateRoute(testUtility.toJson(route))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.message[0]",is("The route needs a name!")));
    }


    private ResultActions invokeCreateRoute(byte [] routeJson) throws Exception {
        return mvc.perform(post("/routes")
        .content(routeJson)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON));
    }


    private ResultActions invokeGetAllRoutes() throws Exception {
        return mvc.perform(get(BASE_PATH)
            .accept(MediaType.APPLICATION_JSON));
    }

    private ResultActions invokeFindRouteById(int id) throws Exception {
        return mvc.perform(get(BASE_PATH+"/"+id)
            .accept(MediaType.APPLICATION_JSON));
    }

    private ResultActions invokeDeleteRouteById(int id) throws Exception {
        return mvc.perform(delete("/routes/"+id)
        .accept(MediaType.APPLICATION_JSON));
    }

}
