package vallegrande.edu.pe.visons.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AuditLogRequest {

    private Integer userId;

    @NotBlank(message = "action es requerido")
    @Size(max = 100, message = "action debe tener como maximo 100 caracteres")
    private String action;

    @NotBlank(message = "tableName es requerido")
    @Size(max = 100, message = "tableName debe tener como maximo 100 caracteres")
    private String tableName;

    private Integer recordId;

    @Size(max = 1000, message = "details debe tener como maximo 1000 caracteres")
    private String details;
}
