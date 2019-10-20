package com.bookshop.order.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookshop.order.model.Cart;
import com.bookshop.order.model.Customer;
import com.bookshop.order.model.Order;
import com.bookshop.order.resource.CartResource;
import com.bookshop.order.resource.CustomerResource;
import com.bookshop.order.service.OrderService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping ("/api/orders")
public class OrderController {

	private final OrderService orderService;
	private final CustomerResource customerResource;
	private final CartResource cartResource;
	//5d333adaaea6182244ed05ef
	//61631s cart
	@GetMapping
	public Flux<Order> getAll(){
		return this.orderService.getAll();
	}
	
	@PostMapping("shopping-cart/{cartId}/customer/{by}")
	public Mono<ResponseEntity<Order>> createOrder(@PathVariable("by") String customerId,
			@PathVariable("cartId") String cartId) {
		return this.orderService.createOrder(customerId, cartId)
				.map(newOrder -> ResponseEntity.created(URI.create("/orders/" + newOrder.getId())).body(newOrder));
	}
	
	@DeleteMapping("{id}")
	public Mono<ResponseEntity<Order>> deleteById(@PathVariable("id") String id){
		return this.orderService.deleteById(id)
		        .map(ResponseEntity::ok)
		        .defaultIfEmpty(ResponseEntity.noContent().build());		
	}
	
	
	@GetMapping ("customer-info/{by}")
	public Mono<ResponseEntity<Customer>> getCustomer(@PathVariable("by") String customerId){
		return this.customerResource.getCustomerById(customerId)
		        .map(ResponseEntity::ok)
		        .defaultIfEmpty(ResponseEntity.noContent().build());
	}	
	
	@GetMapping ("shopping-cart/{cartId}")
	public Mono<ResponseEntity<Cart>> getCart(@PathVariable("cartId") String cartId){
		return this.cartResource.getCartById(cartId)
		        .map(ResponseEntity::ok)
		        .defaultIfEmpty(ResponseEntity.noContent().build());
	}
	
	@PostMapping ("shopping-cart/{cartId}/remove-book-item/{bookId}")
	public Mono<ResponseEntity<Cart>> removeByBookId(@PathVariable("cartId") String cartId, @PathVariable("bookId") String bookId) {
		return this.cartResource.removeBookFromCart(cartId,bookId)
				.map(ResponseEntity::ok)
		        .defaultIfEmpty(ResponseEntity.noContent().build());
	}
	
	@PostMapping ("shopping-cart/{cartId}/add-book-item/{bookId}")
	public Mono<ResponseEntity<Cart>> addByBookId(@PathVariable("cartId") String cartId, @PathVariable("bookId") String bookId) {
		return this.cartResource.addBookToCart(cartId,bookId)
				.map(ResponseEntity::ok)
		        .defaultIfEmpty(ResponseEntity.noContent().build());
	}
	

}
