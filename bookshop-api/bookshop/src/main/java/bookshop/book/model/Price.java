package bookshop.book.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder( toBuilder = true)
@FieldDefaults( level = AccessLevel.PRIVATE)
public class Price {
    @Digits(integer= 1000000000, fraction=2)
    BigDecimal price;
}
