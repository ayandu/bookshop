package bookshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.reactive.function.client.*;

@SpringBootApplication
@EnableTransactionManagement
public class BookshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookshopApplication.class, args);
	}

	@Bean
	public MongoTransactionManager transactionManager(MongoDbFactory dbFactory) {
		return new MongoTransactionManager(dbFactory);
	}

	@Bean
	public WebClient client() {
		return WebClient.builder()
						.baseUrl("http://localhost:8080")
						.build();
	}

	@Bean
	public GridFsTemplate reactiveGridFsTemplate(
			MongoDbFactory MongoDbFactory, MappingMongoConverter mappingMongoConverter) {
		return new GridFsTemplate(MongoDbFactory, mappingMongoConverter);
	}
}
