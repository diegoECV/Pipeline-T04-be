package vallegrande.edu.pe.visons.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import vallegrande.edu.pe.visons.dto.AuditLogRequest;
import vallegrande.edu.pe.visons.model.AuditLog;
import vallegrande.edu.pe.visons.service.AuditLogService;

@RestController
@RequestMapping("/v1/api/audit-logs")
public class AuditLogRest {

    private final AuditLogService auditLogService;

    public AuditLogRest(AuditLogService auditLogService) {
        this.auditLogService = auditLogService;
    }

    @GetMapping({"", "/"})
    public List<AuditLog> findAll() {
        return auditLogService.findAll();
    }

    @GetMapping("/table/{tableName}")
    public List<AuditLog> findByTableName(@PathVariable String tableName) {
        return auditLogService.findByTableName(tableName);
    }

    @GetMapping("/user/{userId}")
    public List<AuditLog> findByUserId(@PathVariable Integer userId) {
        return auditLogService.findByUserId(userId);
    }

    @PostMapping
    public AuditLog register(@Valid @RequestBody AuditLogRequest request) {
        return auditLogService.register(request);
    }
}
