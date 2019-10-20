package com.bookshop.cart.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.bookshop.cart.model.Cart;

public interface CartRepository extends ReactiveMongoRepository<Cart, String> {
}
