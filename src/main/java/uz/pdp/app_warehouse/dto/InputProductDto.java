package uz.pdp.app_warehouse.dto;

import lombok.Data;
import uz.pdp.app_warehouse.entity.Input;
import uz.pdp.app_warehouse.entity.Product;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.sql.Date;

@Data
public class InputProductDto {

    private Integer productId;

    private Double amount;

    private Double price;

    private String expireDate;

    private Integer inputId;
}
