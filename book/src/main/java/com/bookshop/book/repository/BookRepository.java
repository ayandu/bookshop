package com.bookshop.book.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.bookshop.book.model.Book;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookRepository  extends ReactiveMongoRepository<
Book, String>{

	Mono<Book> findFirstByImageNameIgnoreCase(String filename);

	Flux<Book> findByTitleContainingIgnoreCase(String title);

}
