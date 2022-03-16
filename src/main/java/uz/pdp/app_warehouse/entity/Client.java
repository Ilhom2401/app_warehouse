package uz.pdp.app_warehouse.entity;

import lombok.*;
import uz.pdp.app_warehouse.entity.base.Base;

import javax.persistence.Column;
import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@EqualsAndHashCode(callSuper = true)
public class Client extends Base {
    @Column(unique = true, nullable = false)
    private String phoneNumber;
}
