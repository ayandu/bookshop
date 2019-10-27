package bookshop.cart.model;

import bookshop.book.model.Book;
import lombok.Data;
import java.util.List;
import lombok.AccessLevel;
import java.util.ArrayList;
import java.math.BigDecimal;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import javax.validation.constraints.Digits;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@NoArgsConstructor
@FieldDefaults ( level = AccessLevel.PRIVATE)
public class Cart {
	@Id String id;
	@Digits(integer= 1000000000, fraction=2)
	BigDecimal price = new BigDecimal(0);
	List<Book> busket = new ArrayList<>();

	public Cart(String id, List<Book> busket) {
		super();
		this.id = id;
		this.busket = busket;
		this.price = busket.stream().map(Book::getPrice).reduce(price, BigDecimal::add );
	}

	/*
	 *
	 * OPPORTUNITY must keep track of books which were once added but for some reason removed from the
	 *
	 */
	public Cart addBook(Book book) {
		this.busket.add(book);
		return this;
	}
	
	public Cart removeBook(Book book) {
		this.busket.remove(book);
		return this;
	}
}