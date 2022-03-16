package uz.pdp.app_warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.app_warehouse.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
