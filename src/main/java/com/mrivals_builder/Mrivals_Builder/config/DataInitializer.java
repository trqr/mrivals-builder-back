package com.mrivals_builder.Mrivals_Builder.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner loadData(DataSource dataSource) {
        return args -> {
            ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
            populator.addScript(new ClassPathResource("data.sql"));
            populator.execute(dataSource);
        };
    }
}



