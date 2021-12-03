package com.softserve.betterlearningroom.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:/queries.properties")
public class Query {

}
