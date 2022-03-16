package uz.pdp.app_warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.app_warehouse.entity.Input;

public interface InputRepository extends JpaRepository<Input, Integer> {
}
