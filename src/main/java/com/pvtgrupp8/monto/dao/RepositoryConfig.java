package com.pvtgrupp8.monto.dao;

import com.pvtgrupp8.monto.entities.Attraction;
import com.pvtgrupp8.monto.entities.Position;
import com.pvtgrupp8.monto.entities.Route;
import com.pvtgrupp8.monto.entities.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Attraction.class);
        config.exposeIdsFor(Route.class);
        config.exposeIdsFor(Position.class);
        config.exposeIdsFor(User.class);
    }
}
