package com.bookshop.cart.resource;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.bookshop.cart.model.Book;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class BookResource {

	private final WebClient webClient;

	public Mono<Book> getBookById(String bookId) {
		return this.webClient.get().uri("http://book/api/books/"+ bookId).retrieve()
				.bodyToMono(Book.class);
	}
	
	
	
}
