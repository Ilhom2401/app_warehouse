package uz.pdp.app_warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.app_warehouse.entity.InputProduct;

public interface InputProductRepository extends JpaRepository<InputProduct, Integer> {
}
