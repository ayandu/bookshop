package bookshop.customer.controller;

import java.net.URI;

import javax.validation.Valid;

import bookshop.customer.model.Address;
import bookshop.customer.model.Customer;
import bookshop.customer.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping
public class CustomerController {
	
	private final CustomerService customerService;

	public CustomerController (CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@GetMapping("customers")
	public Flux<Customer> getAll(){
		return this.customerService.getAll();
	}
	
	@GetMapping("customer/{id}")
	public Mono<ResponseEntity<Customer>> getById(@PathVariable("id") String id){
		return this.customerService.getById(id)
			      .map(ResponseEntity::ok)
			        .defaultIfEmpty(ResponseEntity.noContent().build());
	}
	
	@PostMapping("customer")
	public Mono<ResponseEntity<Customer>> createCustomer(@RequestBody @Valid Customer newCustomer){
		return this.customerService.create(newCustomer)
				.map(customer -> ResponseEntity.created(URI.create("/customers/" + customer.getId())).body(customer));
	}

	@PatchMapping("customer/{id}")
	public Mono<ResponseEntity<Customer>> updateAddress(@PathVariable String id, @RequestBody @Valid Address address){
		return this.customerService.updateAddress(id, address)
				.map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.noContent().build());
	}

	@GetMapping("customer?email={email}")
	public Flux<Customer> searchByEmail(@PathVariable("email") String email) {
	    return this.customerService.searchByEmail(email);
	}
	  @PutMapping("customer/{id}")
	  public Mono<ResponseEntity<Customer>> updateCustomer(@PathVariable String id, @RequestBody @Valid Customer updatedCustomer) {
	    return this.customerService.update(id, updatedCustomer)
	        .map(ResponseEntity::ok)
	        .defaultIfEmpty(ResponseEntity.noContent().build());
	  }
	  
	  @DeleteMapping("customer/{id}")
	  public Mono<ResponseEntity<Void>> deleteCustomer(@PathVariable String id) {
	    return customerService.deleteById(id)
	        .map(r -> ResponseEntity.ok().<Void>build())
	        .defaultIfEmpty(ResponseEntity.noContent().build());
	  }
	  
}
