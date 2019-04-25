package com.pvtgrupp8.monto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SpringBootTomcatApplication extends SpringBootServletInitializer {

    private static Class<SpringBootTomcatApplication> application = SpringBootTomcatApplication.class;

    public static void main(String[] args) {
        SpringApplication.run(application);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(application);
    }

}
