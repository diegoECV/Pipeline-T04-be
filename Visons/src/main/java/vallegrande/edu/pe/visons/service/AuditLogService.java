package vallegrande.edu.pe.visons.service;

import java.util.List;

import vallegrande.edu.pe.visons.dto.AuditLogRequest;
import vallegrande.edu.pe.visons.model.AuditLog;

public interface AuditLogService {

    List<AuditLog> findAll();

    List<AuditLog> findByTableName(String tableName);

    List<AuditLog> findByUserId(Integer userId);

    AuditLog register(AuditLogRequest request);

    AuditLog register(Integer userId, String action, String tableName, Integer recordId, String details);
}
