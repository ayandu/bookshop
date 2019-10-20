package com.bookshop.order.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.bookshop.order.model.Order;

public interface OrderRepository extends ReactiveMongoRepository<Order, String> {

}
