package fooddeliveryservice.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class RegisterMenuCommand {

    private String menuId;
    private String name;
    private Money price;
    private String description;
}
