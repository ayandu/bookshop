package bookshop.cart.resource;

import bookshop.book.model.Book;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class BookResource {

	private final WebClient webClient;

	public Mono<Book> getBookById(String bookId) {
		return this.webClient.get().uri("/book/"+ bookId).retrieve()
				.bodyToMono(Book.class);
	}
	
	
	
}
