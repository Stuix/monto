package com.pvtgrupp8.monto;

import com.pvtgrupp8.monto.dao.RouteRepository;
import com.pvtgrupp8.monto.entities.Route;
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
public class RouteTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RouteRepository routeRepository;

    @Test
    public void testEmptyRepository(){
        List<Route> routes =routeRepository.findAll();
        assertTrue(routes.isEmpty());
    }

    @Test
    public void testAddToRepository() {
        List<Route> routes = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Route x = new Route();
            x.setRouteName("" + i);
            x.setDescription("Lorem Ipsum" + i);
            x.setRouteIsPublic(i % 2 == 0);
            routes.add(x);
            routeRepository.save(x);

        }

        for (int i = 0; i < 20; i++) {
            Route temp = routes.get(i);
            Route repoTemp = routeRepository.findById(temp.getId());
            assertSame(temp, repoTemp);
            assertEquals(temp.getRouteName(), repoTemp.getRouteName());
            assertEquals(temp.getDescription(), repoTemp.getDescription());
            assertEquals(temp.isRouteIsPublic(), repoTemp.isRouteIsPublic());
        }

    }

}
