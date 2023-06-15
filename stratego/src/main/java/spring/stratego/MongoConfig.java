package spring.stratego;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

@Configuration
public class MongoConfig {

    private static final String databaseName = "stratego";

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create("mongodb+srv://stratego2023:stratego2023@cluster0.49ega6i.mongodb.net/stratego?retryWrites=true&w=majority");
    }

    @Bean
    public MongoDatabase mongoDatabase() {
        return mongoClient().getDatabase(databaseName);
    }
}
