package spring.stratego;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoConfig {

    private static final String databaseName = "stratego";

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create("mongodb+srv://stratego2023:stratego2023@stratego.xsjzd7p.mongodb.net/?retryWrites=true&w=majority");
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), databaseName);
    }
}
