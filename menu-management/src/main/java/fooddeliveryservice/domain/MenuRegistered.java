package fooddeliveryservice.domain;

import fooddeliveryservice.domain.*;
import fooddeliveryservice.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class MenuRegistered extends AbstractEvent {

    private String menuId;
    private String name;
    private Money price;
    private String description;

    public MenuRegistered(Menu aggregate) {
        super(aggregate);
    }

    public MenuRegistered() {
        super();
    }
}
//>>> DDD / Domain Event
