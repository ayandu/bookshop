package com.bookshop.customer.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookshop.customer.model.Customer;
import com.bookshop.customer.service.CustomerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	
	private final CustomerService customerService;

	public CustomerController (CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@GetMapping
	public Flux<Customer> getAll(){
		return this.customerService.getAll();
	}
	
	@GetMapping("/{id}")
	public Mono<ResponseEntity<Customer>> getById(@PathVariable("id") String id){
		return this.customerService.getById(id)
			      .map(ResponseEntity::ok)
			        .defaultIfEmpty(ResponseEntity.noContent().build());
	}
	
	@PostMapping
	public Mono<ResponseEntity<Customer>> createCustomer(@RequestBody @Valid Customer newCustomer){
		return this.customerService.create(newCustomer)
				.map(customer -> ResponseEntity.created(URI.create("/customers/" + customer.getId())).body(customer));
	}
	
	@GetMapping("?email={email}")
	public Flux<Customer> searchByEmail(@PathVariable("email") String email) {
	    return this.customerService.searchByEmail(email);
	}
	  @PutMapping("/{id}")
	  public Mono<ResponseEntity<Customer>> updateCustomer(@PathVariable String id, @RequestBody @Valid Customer updatedCustomer) {
	    return this.customerService.update(id, updatedCustomer)
	        .map(ResponseEntity::ok)
	        .defaultIfEmpty(ResponseEntity.noContent().build());
	  }
	  
	  @DeleteMapping("/{id}")
	  public Mono<ResponseEntity<Void>> deleteCustomer(@PathVariable String id) {
	    return customerService.deleteById(id)
	        .map(r -> ResponseEntity.ok().<Void>build())
	        .defaultIfEmpty(ResponseEntity.noContent().build());
	  }
	  
}
