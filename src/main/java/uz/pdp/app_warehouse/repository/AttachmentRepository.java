package uz.pdp.app_warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.app_warehouse.entity.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {
}
