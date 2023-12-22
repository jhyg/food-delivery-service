package fooddeliveryservice.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class PlaceOrderCommand {

    private String customerId;
    private String restaurantId;
    private String menu;
    private Integer quantity;
    private Money payment;
}
