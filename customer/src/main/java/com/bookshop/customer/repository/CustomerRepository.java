package com.bookshop.customer.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.bookshop.customer.model.Customer;

import reactor.core.publisher.Flux;

public interface CustomerRepository extends ReactiveMongoRepository<Customer,String>{

	Flux<Customer> findByEmailLikeIgnoreCase(String email);

}
