package uz.pdp.app_warehouse.entity;

import lombok.*;
import uz.pdp.app_warehouse.entity.base.Base;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@Getter
@Setter
@Entity
@EqualsAndHashCode(callSuper = true)
public class Measurement extends Base {

}
