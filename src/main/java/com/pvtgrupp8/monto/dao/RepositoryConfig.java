package com.pvtgrupp8.monto.dao;

import com.pvtgrupp8.monto.entities.Attraction;
import com.pvtgrupp8.monto.entities.Creator;
import com.pvtgrupp8.monto.entities.Position;
import com.pvtgrupp8.monto.entities.Route;
import com.pvtgrupp8.monto.entities.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer  {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Attraction.class);
        config.exposeIdsFor(Route.class);
        config.exposeIdsFor(Position.class);
        config.exposeIdsFor(User.class);
        config.exposeIdsFor(Creator.class);
    }


    /*
The Problem:
No 'Access-Control-Allow-Origin' header is present on the requested resource.
Origin 'http://internet.derp' is therefore not allowed access.

The Solution:
A CORS proxy!

$.getJSON('https://httpbin.org/get?cors=a_problem',function(){})

becomes ..

$.getJSON('http://cors.io/?https://httpbin.org/get?cors=a_problem',function(){})

$.post('https://httpbin.org/post',data,function(){})

becomes ..

$.post('http://cors.io/?https://httpbin.org/post',data,function(){})

send hatemail to @deanpierce
contributors: @nizarmah_*/
}
