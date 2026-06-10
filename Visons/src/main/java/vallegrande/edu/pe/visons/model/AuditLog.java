package vallegrande.edu.pe.visons.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "AUDIT_LOG")
@JsonInclude(JsonInclude.Include.ALWAYS)
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Integer logId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "action", length = 100)
    private String action;

    @Column(name = "table_name", length = 100)
    private String tableName;

    @Column(name = "record_id")
    private Integer recordId;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "details")
    private String details;
}
