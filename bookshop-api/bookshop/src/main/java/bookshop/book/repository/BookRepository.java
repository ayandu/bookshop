package bookshop.book.repository;

import bookshop.book.model.Book;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookRepository  extends ReactiveMongoRepository<Book, String>{

	Mono<Book> findFirstByImageNameIgnoreCase(String filename);

	Flux<Book> findByTitleContainingIgnoreCase(String title);
	
	Mono<Book> findByTitleIgnoreCase(String title);

}
