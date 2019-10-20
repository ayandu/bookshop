package com.bookshop.order.resource;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.bookshop.order.model.Customer;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CustomerResource{
	
	private final WebClient webClient;
	
	public Mono<Customer> getCustomerById(String id){
		return this.webClient.get().uri("http://customer/api/customers/"+id).retrieve()
				.bodyToMono(Customer.class);
	}

}
