package uz.pdp.app_warehouse.entity;

import lombok.*;
import uz.pdp.app_warehouse.entity.base.Base;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Currency extends Base {

}
