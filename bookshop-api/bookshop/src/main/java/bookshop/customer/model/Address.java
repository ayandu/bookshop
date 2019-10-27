package bookshop.customer.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder( toBuilder = true)
@FieldDefaults( level = AccessLevel.PRIVATE)
public class Address {
    String address;
}
