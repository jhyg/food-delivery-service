package fooddeliveryservice.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class AcceptOrderCommand {

    private String deliveryId;
    private String orderId;
    private String driverId;
    private Address address;
}
