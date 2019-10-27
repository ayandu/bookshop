package bookshop.order.resource;

import bookshop.customer.model.Customer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CustomerResource{
	
	private final WebClient webClient;
	
	public Mono<Customer> getCustomerById(String id){
		return this.webClient.get()
				.uri("/customer/"+id)
				.retrieve()
				.bodyToMono(Customer.class);
	}

}
