package bookshop.cart.controller;

import java.net.URI;
import java.math.BigDecimal;
import javax.validation.Valid;

import bookshop.cart.model.Cart;
import bookshop.cart.service.CartService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CartController {

	private final CartService cartService;
	
	@GetMapping("carts")
	public Flux<Cart> getAll(){
		return this.cartService.getAll();
	}
	
	@GetMapping("cart/{id}")
	public Mono<Cart> getById(@PathVariable("id") String id){
		return this.cartService.getById(id);
	}
	
	@PostMapping("cart")
	public Mono<ResponseEntity<Cart>> createCart(@RequestBody @Valid Cart newCart){
		return this.cartService.create(newCart)
				.map(cart -> ResponseEntity.created(URI.create("/cart/" + cart.getId())).body(cart));
		
	}
	
	@PutMapping("cart/{id}")
	public Mono<ResponseEntity<Cart>> updateCart(@PathVariable String id, @RequestBody @Valid Cart updatedCart) {
	    return this.cartService.update(id, updatedCart)
	        .map(ResponseEntity::ok)
	        .defaultIfEmpty(ResponseEntity.noContent().build());
	 }
	
	@DeleteMapping("cart/{id}")
	public Mono<ResponseEntity<Void>> deleteCart(@PathVariable("id") String id) {
	    return cartService.deleteById(id)
	        .map(r -> ResponseEntity.ok().<Void>build())
	        .defaultIfEmpty(ResponseEntity.noContent().build());
	 }

	@DeleteMapping("carts/delete")
	public Mono<ResponseEntity<Void>> deleteCart() {
		return cartService.deleteAll()
				.map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.noContent().build());
	}

	/*@GetMapping("price/{id}")
	public Mono<ResponseEntity<BigDecimal>> getPriceById(@PathVariable("id") String id){
		return this.cartService.getPrice(id)
				.map(ResponseEntity::ok)
		        .defaultIfEmpty(ResponseEntity.noContent().build());
	}*/
	
	@PostMapping("cart/{id}/remove-book/{bookId}")
	public Mono<ResponseEntity<Cart>> removeBookFromCart(@PathVariable("id") String id, @PathVariable("bookId") String bookId){
		return this.cartService.removeBookFromCart(id, bookId)	        
				.map(ResponseEntity::ok)
		        .defaultIfEmpty(ResponseEntity.noContent().build());
	}
	
	@PostMapping("cart/{id}/book/{bookId}")
	public Mono<ResponseEntity<Cart>> addBookToCart(@PathVariable("id") String id, @PathVariable("bookId") String bookId){
		return this.cartService.addBookToCart(id, bookId)	        
				.map(ResponseEntity::ok)
		        .defaultIfEmpty(ResponseEntity.noContent().build());
	}
}
