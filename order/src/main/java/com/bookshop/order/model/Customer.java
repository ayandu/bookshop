package com.bookshop.order.model;

import javax.validation.constraints.Email;

import org.springframework.data.annotation.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder ( toBuilder = true)
@FieldDefaults ( level = AccessLevel.PRIVATE)
public class Customer {
	@EqualsAndHashCode.Exclude
	@Id String id;
	@Email String email;
	String name;
	String address;
	String phone;
}
