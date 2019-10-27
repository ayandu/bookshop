package bookshop.order.service;

import bookshop.order.model.Order;
import bookshop.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository repository;

	public Mono<Order> createOrder(Order order){
	return this.repository.save(order);
	}

	public Flux<Order> getAll() {
		return this.repository.findAll();
	}

	public Mono<Order> deleteById(String id) {
		return this.repository.findById(id).flatMap( order -> this.repository.deleteById(id).thenReturn(order));
	}


	public Mono<Order> getById(String id) {
		return this.repository.findById(id);
	}
}
