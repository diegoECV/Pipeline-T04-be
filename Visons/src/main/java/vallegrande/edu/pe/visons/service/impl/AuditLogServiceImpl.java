package vallegrande.edu.pe.visons.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import vallegrande.edu.pe.visons.dto.AuditLogRequest;
import vallegrande.edu.pe.visons.model.AuditLog;
import vallegrande.edu.pe.visons.repository.AuditLogRepository;
import vallegrande.edu.pe.visons.service.AuditLogService;

@Service
public class AuditLogServiceImpl implements AuditLogService {

    private final AuditLogRepository auditLogRepository;

    public AuditLogServiceImpl(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    @Override
    public List<AuditLog> findAll() {
        return auditLogRepository.findAll(Sort.by(Sort.Direction.DESC, "date"));
    }

    @Override
    public List<AuditLog> findByTableName(String tableName) {
        return auditLogRepository.findByTableNameIgnoreCaseOrderByDateDesc(requireText(tableName, "tableName"));
    }

    @Override
    public List<AuditLog> findByUserId(Integer userId) {
        return auditLogRepository.findByUserIdOrderByDateDesc(userId);
    }

    @Override
    public AuditLog register(AuditLogRequest request) {
        if (request == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Audit log request invalido");
        }

        return register(request.getUserId(), request.getAction(), request.getTableName(), request.getRecordId(),
                request.getDetails());
    }

    @Override
    public AuditLog register(Integer userId, String action, String tableName, Integer recordId, String details) {
        AuditLog auditLog = new AuditLog();
        auditLog.setUserId(userId);
        auditLog.setAction(requireText(action, "action"));
        auditLog.setTableName(requireText(tableName, "tableName"));
        auditLog.setRecordId(recordId);
        auditLog.setDate(LocalDateTime.now());
        auditLog.setDetails(normalizeNullable(details));
        return auditLogRepository.save(auditLog);
    }

    private String requireText(String value, String fieldName) {
        String normalized = normalizeNullable(value);
        if (normalized == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, fieldName + " es requerido");
        }
        return normalized;
    }

    private String normalizeNullable(String value) {
        if (value == null) {
            return null;
        }
        String trimmed = value.trim();
        return trimmed.isBlank() ? null : trimmed;
    }
}
