package com.bookshop.order.resource;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.bookshop.order.model.Cart;

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
				.uri("http://cart/api/carts/" + cartId)
				.retrieve()
				.bodyToMono(Cart.class);
	}

	public Mono<Cart> removeBookFromCart(String cartId, String bookId) {
		return this.webClient.post()
				.uri("http://cart/api/carts/"+ cartId + "/remove-book-item/" + bookId )
				.retrieve().bodyToMono(Cart.class);
	}

	public Mono<Cart> addBookToCart(String cartId, String bookId) {
		return this.webClient.post()
				.uri("http://cart/api/carts/"+ cartId + "/add-book-item/" + bookId )
				.retrieve().bodyToMono(Cart.class);
	}

}
