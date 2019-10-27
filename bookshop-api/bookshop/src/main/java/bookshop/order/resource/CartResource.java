package bookshop.order.resource;

import bookshop.cart.model.Cart;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CartResource {
	
	private final WebClient webClient;

	public Mono<Cart> getCartById(String cartId) {
		return this
				.webClient
				.get()
				.uri("/cart/" + cartId)
				.retrieve()
				.bodyToMono(Cart.class);
	}

	public Mono<Cart> removeBookFromCart(String cartId, String bookId) {
		return this.webClient.post()
				.uri("/cart/"+ cartId + "/remove-book/" + bookId )
				.retrieve().bodyToMono(Cart.class);
	}

	public Mono<Cart> addBookToCart(String cartId, String bookId) {
		return this.webClient.post()
				.uri("/cart/"+ cartId + "/book/" + bookId )
				.retrieve().bodyToMono(Cart.class);
	}

}
