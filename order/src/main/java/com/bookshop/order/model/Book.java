package com.bookshop.order.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.Digits;

import org.springframework.data.annotation.Id;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder ( toBuilder = true)
@FieldDefaults ( level = AccessLevel.PRIVATE)
public class Book {
	@EqualsAndHashCode.Exclude
	@Id String id;
	String title;
	String publisher;
	List<String> category;
	List<String> author;
	@Builder.Default LocalDate arrivalDate = LocalDate.now();
	LocalDate releasedDate;
	@Digits(integer= 1000000000, fraction=2)
	BigDecimal price;
	int quantity;
	String imageName;
}
