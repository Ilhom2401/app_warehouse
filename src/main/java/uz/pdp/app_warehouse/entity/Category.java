package uz.pdp.app_warehouse.entity;

import lombok.*;
import uz.pdp.app_warehouse.entity.base.Base;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@EqualsAndHashCode(callSuper = true)
public class Category extends Base {

    @ManyToOne
    private Category parentCategory;
}
