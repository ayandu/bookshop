package bookshop.cart.service;

import bookshop.cart.model.Cart;
import bookshop.cart.repository.CartRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CartService {

	private final CartRepository cartRepository;

	public Flux<Cart> getAll(){
		  return cartRepository.findAll();
	}

	public Mono<Cart> getById(String id){
		return this.cartRepository.findById(id);
	}

	public Mono<Cart> create(Cart newCart) {
		return cartRepository.save(newCart);
	}

	public Mono<Cart> update(String id, Cart updatedCart) {
		return cartRepository.findById(id)
		     .map(existingCart -> new Cart(existingCart.getId(), updatedCart.getBookIdBasket()))
		     .flatMap(cartRepository::save);
	 }
	
	public Mono<Cart> deleteById(String id) {
	    return cartRepository.findById(id)
	        .flatMap(cart -> cartRepository.delete(cart).then(Mono.just(cart)));
	}
	/*
	public Mono<BigDecimal> getPrice(String id){
		return this.cartRepository.findById(id)
			.flatMap( cart -> Mono.just(cart.getPrice()));
	}*/

	public Mono<Cart> removeBookFromCart(String id, String bookId) {																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																				
		return this.cartRepository.findById(id)
				.map(cart -> cart.removeBook(bookId))
				.flatMap( cart -> this.update(id, cart));
	}

	public Mono<Cart> addBookToCart(String id, String bookId) {
		return this.cartRepository.findById(id)
				.map(cart -> cart.addBook(bookId))
				.flatMap( cart -> this.update(id, cart));
	}

	public Mono<Void> deleteAll() {
		return this.cartRepository.deleteAll();
	}
}
