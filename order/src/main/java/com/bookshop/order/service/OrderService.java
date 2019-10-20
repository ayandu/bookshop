package com.bookshop.order.service;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import org.springframework.stereotype.Service;

import com.bookshop.order.model.Order;
import com.bookshop.order.repository.OrderRepository;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository repository;

	public Mono<Order> createOrder(String customerId, String cartId){
	
	return this.repository.save(Order.builder()
				.customerId(customerId)
				.cartId(cartId)
				.createdDate(LocalDate.now())
				.build());
	}

	public Flux<Order> getAll() {
		return this.repository.findAll();
	}

	public Mono<Order> deleteById(String id) {
		return this.repository.findById(id).flatMap( order -> this.repository.deleteById(id).thenReturn(order));
	}


}
