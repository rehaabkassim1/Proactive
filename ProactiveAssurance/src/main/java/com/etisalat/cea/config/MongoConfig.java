package com.etisalat.cea.config;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
@EnableMongoRepositories(basePackages = {"com.etisalat.cea.repositories"})
public class MongoConfig {

  @Autowired
  public Environment environment;
	
  @Bean
  MongoClient mongoClient() {
    ConnectionString connectionString
        = new ConnectionString(environment.getProperty("mongodb.connection.uri","mongodb://localhost:27017"));

    MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
        .applyConnectionString(connectionString)
        .applyToConnectionPoolSettings(builder -> builder.maxSize(20)
            .minSize(10)
            .maxWaitTime(2000, TimeUnit.MILLISECONDS)
            .build())
        .build();
    return MongoClients.create(mongoClientSettings);
  }

  @Bean
  MongoTemplate mongoTemplate(MongoClient mongoClient) {
    return new MongoTemplate(mongoClient, environment.getProperty("mongodb.database.name","testdb"));
  }
  
}
