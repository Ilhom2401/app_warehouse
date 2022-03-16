package uz.pdp.app_warehouse.entity;

import lombok.*;
import uz.pdp.app_warehouse.entity.base.Base;

import javax.persistence.Entity;

@AllArgsConstructor
@Getter
@Setter
@Entity
@EqualsAndHashCode(callSuper = true)
public class Warehouse extends Base {
}
