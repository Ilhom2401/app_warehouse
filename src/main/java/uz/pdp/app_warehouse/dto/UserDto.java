package uz.pdp.app_warehouse.dto;

import lombok.Data;
import uz.pdp.app_warehouse.entity.Warehouse;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import java.util.List;
import java.util.Set;

@Data
public class UserDto {
    private String firstName;

    private String lastName;

    @Column(unique = true, nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String password;

    private List<Integer> warehousesId;
}
