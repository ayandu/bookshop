package bookshop.customer.service;

import bookshop.customer.model.Address;
import bookshop.customer.model.Customer;
import bookshop.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.rmi.MarshalledObject;

@Service
public class CustomerService {

	  private final CustomerRepository customerRepository;
	  
	  public CustomerService(CustomerRepository customerRepository) {
		  this.customerRepository = customerRepository;
	  }
	  
	  public Flux<Customer> getAll(){
		  return customerRepository.findAll();
	  }
	  
	  public Flux<Customer> searchByEmail(String email){
		  return this.customerRepository.findByEmailLikeIgnoreCase(email);
	  }
	  
	  public Mono<Customer> getById(String id) {
		    return customerRepository.findById(id);
	  }
	  
	  public Mono<Customer> create(Customer newCustomer) {
		    return customerRepository.save(Customer.builder().id(null)
		    		.email(newCustomer.getEmail())
		    		.address(newCustomer.getAddress())
		    		.phone(newCustomer.getPhone())
		    		.name(newCustomer.getName()).build());
	  }

	  public Mono<Customer> updateAddress(String id, Address address){
	  	return this.customerRepository.findById(id).flatMap( book ->  this.customerRepository.save(book.toBuilder().address(address.getAddress()).build()));
	  }
	  
	  public Mono<Customer> update(String id, Customer updatedCustomer) {
		    return customerRepository.findById(id)
		        .map(existingCustomer -> existingCustomer.toBuilder()
		              .email(updatedCustomer.getEmail())
		              .address(updatedCustomer.getAddress())
		              .name(updatedCustomer.getName())
		              .phone(updatedCustomer.getPhone())
		              .build())
		        .flatMap(customerRepository::save);
	 }
	  
	public Mono<Customer> deleteById(String id) {
		    return customerRepository.findById(id)
		        .flatMap(customer -> customerRepository.delete(customer).then(Mono.just(customer)));
	}
}
