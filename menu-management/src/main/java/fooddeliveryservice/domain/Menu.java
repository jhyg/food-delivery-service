package fooddeliveryservice.domain;

import fooddeliveryservice.MenuManagementApplication;
import fooddeliveryservice.domain.MenuRegistered;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Menu_table")
@Data
//<<< DDD / Aggregate Root
public class Menu {

    @Id
    private String menuId;

    private String name;

    private Money price;

    private String description;

    @PostPersist
    public void onPostPersist() {
        MenuRegistered menuRegistered = new MenuRegistered(this);
        menuRegistered.publishAfterCommit();
    }

    @PrePersist
    public void onPrePersist() {}

    public static MenuRepository repository() {
        MenuRepository menuRepository = MenuManagementApplication.applicationContext.getBean(
            MenuRepository.class
        );
        return menuRepository;
    }
}
//>>> DDD / Aggregate Root
