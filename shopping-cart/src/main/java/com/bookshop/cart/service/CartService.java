package com.bookshop.cart.service;

import java.math.BigDecimal;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.bookshop.cart.model.Cart;
import com.bookshop.cart.repository.CartRepository;
import com.bookshop.cart.resource.BookResource;

@Service
@RequiredArgsConstructor
public class CartService {

	private final CartRepository cartRepository;
	private final BookResource bookResource;
	
	public Flux<Cart> getAll(){
		  return cartRepository.findAll();
	}
	
	public Mono<Cart> getById(String id){
		return this.cartRepository.findById(id);
	}
	
	public Mono<Cart> create(Cart newCart) {
		return cartRepository.save( new Cart( null, newCart.getBusket()));
	}
	
	public Mono<Cart> update(String id, Cart updatedCart) {
		return cartRepository.findById(id)
		     .map(existingCart -> new Cart(existingCart.getId(), updatedCart.getBusket()))
		     .flatMap(cartRepository::save);
	 }
	
	public Mono<Cart> deleteById(String id) {
	    return cartRepository.findById(id)
	        .flatMap(cart -> cartRepository.delete(cart).then(Mono.just(cart)));
	}
			
	public Mono<BigDecimal> getPrice(String id){
		return this.cartRepository.findById(id)
			.map( cart -> cart.getPrice());	
	}

	public Mono<Cart> removeBookFromCart(String id, String bookId) {																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																				
		return this.cartRepository.findById(id)
				.map(cart -> 
					this.bookResource.getBookById(bookId).map( book -> cart.removeBook(book)))
				.flatMap( mono -> mono.flatMap( cart -> this.update(id, cart)));
	}

	public Mono<Cart> addBookToCart(String id, String bookId) {
		return this.cartRepository.findById(id)
				.map(cart -> 
				this.bookResource.getBookById(bookId).map( book -> cart.addBook(book)))
				.flatMap( mono -> mono.flatMap( cart -> this.update(id, cart)));
	}
}
