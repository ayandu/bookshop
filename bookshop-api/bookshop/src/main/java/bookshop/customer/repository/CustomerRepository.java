package bookshop.customer.repository;

import bookshop.customer.model.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface CustomerRepository extends ReactiveMongoRepository<Customer,String>{

	Flux<Customer> findByEmailLikeIgnoreCase(String email);

}
