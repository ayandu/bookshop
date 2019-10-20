package com.bookshop.order.model;

import lombok.Data;
import java.util.List;
import lombok.AccessLevel;
import java.math.BigDecimal;
import java.util.ArrayList;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import javax.validation.constraints.Digits;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@FieldDefaults ( level = AccessLevel.PRIVATE)
public class Cart {
	@EqualsAndHashCode.Exclude
	@Id String id;
	@Digits(integer= 1000000000, fraction=2)
	BigDecimal price = new BigDecimal(0);
	List<Book> busket = new ArrayList<>();
	
	public Cart(String id, List<Book> busket) {
		super();
		this.id = id;
		this.busket = busket;
		this.price = busket.stream().map( book -> book.getPrice()).reduce(price, BigDecimal::add );
	}
}
