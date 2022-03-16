package uz.pdp.app_warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.app_warehouse.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
