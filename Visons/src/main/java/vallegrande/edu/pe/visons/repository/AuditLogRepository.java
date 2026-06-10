package vallegrande.edu.pe.visons.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vallegrande.edu.pe.visons.model.AuditLog;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Integer> {

    List<AuditLog> findByTableNameIgnoreCaseOrderByDateDesc(String tableName);

    List<AuditLog> findByUserIdOrderByDateDesc(Integer userId);
}
