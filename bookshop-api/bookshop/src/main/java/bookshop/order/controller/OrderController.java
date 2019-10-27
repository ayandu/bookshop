package bookshop.order.controller;

import java.math.BigDecimal;
import java.net.URI;

import bookshop.cart.model.Cart;
import bookshop.customer.model.Customer;
import bookshop.order.model.Order;
import bookshop.order.resource.CartResource;
import bookshop.order.resource.CustomerResource;
import bookshop.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;
	private final CustomerResource customerResource;
	private final CartResource cartResource;

	@GetMapping("orders")
	public Flux<Order> getAll(){
		return this.orderService.getAll();
	}

	@GetMapping("order/{id}")
	public Mono<ResponseEntity<Order>> getById(@PathVariable("id") String id){
		return this.orderService.getById(id)
				.map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.noContent().build());
	}

	@PostMapping("/order")
	public Mono<ResponseEntity<Order>> createOrder(@RequestBody @Valid Order order) {
		return this.orderService.createOrder(order)
				.map(newOrder -> ResponseEntity.created(URI.create("/order/" + newOrder.getId())).body(newOrder));
	}

	@DeleteMapping("{id}")
	public Mono<ResponseEntity<Order>> deleteById(@PathVariable("id") String id){
		return this.orderService.deleteById(id)
		        .map(ResponseEntity::ok)
		        .defaultIfEmpty(ResponseEntity.noContent().build());		
	}
	
	
	@GetMapping ("/customer-info/{by}")
	public Mono<ResponseEntity<Customer>> getCustomer(@PathVariable("by") String customerId){
		return this.customerResource.getCustomerById(customerId)
		        .map(ResponseEntity::ok)
		        .defaultIfEmpty(ResponseEntity.noContent().build());
	}	
	
	@GetMapping ("/cart-info/{cartId}")
	public Mono<ResponseEntity<Cart>> getCart(@PathVariable("cartId") String cartId){
		return this.cartResource.getCartById(cartId)
		        .map(ResponseEntity::ok)
		        .defaultIfEmpty(ResponseEntity.noContent().build());
	}
	
	@PostMapping ("/shopping-cart/{cartId}/remove-book/{bookId}")
	public Mono<ResponseEntity<Cart>> removeByBookId(@PathVariable("cartId") String cartId, @PathVariable("bookId") String bookId) {
		return this.cartResource.removeBookFromCart(cartId,bookId)
				.map(ResponseEntity::ok)
		        .defaultIfEmpty(ResponseEntity.noContent().build());
	}
	
	@PostMapping ("/shopping-cart/{cartId}/book/{bookId}")
	public Mono<ResponseEntity<Cart>> addByBookId(@PathVariable("cartId") String cartId, @PathVariable("bookId") String bookId) {
		return this.cartResource.addBookToCart(cartId,bookId)
				.map(ResponseEntity::ok)
		        .defaultIfEmpty(ResponseEntity.noContent().build());
	}
	

}
