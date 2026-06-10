package vallegrande.edu.pe.visons.dto;

import lombok.Data;

@Data
public class RoleResponse {
    private Integer roleId;
    private String name;
    private String description;
    private Long userCount;
    private Boolean assignable;
}
