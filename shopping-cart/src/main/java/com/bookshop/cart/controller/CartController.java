package com.bookshop.cart.controller;

import java.net.URI;
import java.math.BigDecimal;
import javax.validation.Valid;
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

import com.bookshop.cart.model.Cart;
import com.bookshop.cart.service.CartService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/carts")
public class CartController {

	private final CartService cartService;
	
	@GetMapping
	public Flux<Cart> getAll(){
		return this.cartService.getAll();
	}
	
	@GetMapping("{id}")
	public Mono<Cart> getById(@PathVariable("id") String id){
		return this.cartService.getById(id);
	}
	
	@PostMapping
	public Mono<ResponseEntity<Cart>> createCart(@RequestBody @Valid Cart newCart){
		return this.cartService.create(newCart)
				.map(cart -> ResponseEntity.created(URI.create("/carts/" + cart.getId())).body(cart));
		
	}
	
	@PutMapping("{id}")
	public Mono<ResponseEntity<Cart>> updateCart(@PathVariable String id, @RequestBody @Valid Cart updatedCart) {
	    return this.cartService.update(id, updatedCart)
	        .map(ResponseEntity::ok)
	        .defaultIfEmpty(ResponseEntity.noContent().build());
	 }
	
	@DeleteMapping("{id}")
	public Mono<ResponseEntity<Void>> deleteCart(@PathVariable("id") String id) {
	    return cartService.deleteById(id)
	        .map(r -> ResponseEntity.ok().<Void>build())
	        .defaultIfEmpty(ResponseEntity.noContent().build());
	 }
	
	@GetMapping("price/{id}")
	public Mono<ResponseEntity<BigDecimal>> getPriceById(@PathVariable("id") String id){
		return this.cartService.getPrice(id)
				.map(ResponseEntity::ok)
		        .defaultIfEmpty(ResponseEntity.noContent().build());
	}
	
	@PostMapping("{id}/remove-book-item/{bookId}")
	public Mono<ResponseEntity<Cart>> removeBookFromCart(@PathVariable("id") String id, @PathVariable("bookId") String bookId){
		return this.cartService.removeBookFromCart(id, bookId)	        
				.map(ResponseEntity::ok)
		        .defaultIfEmpty(ResponseEntity.noContent().build());
	}
	
	@PostMapping("{id}/add-book-item/{bookId}")
	public Mono<ResponseEntity<Cart>> addBookToCart(@PathVariable("id") String id, @PathVariable("bookId") String bookId){
		return this.cartService.addBookToCart(id, bookId)	        
				.map(ResponseEntity::ok)
		        .defaultIfEmpty(ResponseEntity.noContent().build());
	}
}
