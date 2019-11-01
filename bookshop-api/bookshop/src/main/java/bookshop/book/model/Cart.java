package bookshop.cart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;
import lombok.AccessLevel;
import java.util.ArrayList;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import reactor.core.publisher.Flux;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults ( level = AccessLevel.PRIVATE)
public class Cart {
	@Id String id;
	List<String> bookIdBasket = new ArrayList<>();

	/*
	 *
	 * OPPORTUNITY must keep track of books which were once added but for some reason removed from the
	 *
	 */
	public Cart addBook(String id) {
		this.bookIdBasket.add(id);
		return this;
	}
	
	public Cart removeBook(String id) {
		this.bookIdBasket.remove(id);
		return this;
	}
}