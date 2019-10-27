package bookshop.order.model;

import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
@Builder ( toBuilder = true)
@FieldDefaults ( level = AccessLevel.PRIVATE)
public class Order {
	@EqualsAndHashCode.Exclude
	@Id String id;
	String customerId;
	@Builder.Default LocalDate createdDate = LocalDate.now() ;
	String cartId;
}
