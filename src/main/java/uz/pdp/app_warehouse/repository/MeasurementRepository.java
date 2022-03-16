package uz.pdp.app_warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.app_warehouse.entity.Measurement;

public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {

}
