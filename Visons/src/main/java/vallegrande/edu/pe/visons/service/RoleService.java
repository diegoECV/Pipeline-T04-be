package vallegrande.edu.pe.visons.service;

import java.util.List;
import java.util.Optional;

import vallegrande.edu.pe.visons.dto.RoleRequest;
import vallegrande.edu.pe.visons.dto.RoleResponse;
import vallegrande.edu.pe.visons.dto.UserResponse;

public interface RoleService {

    List<RoleResponse> findAll();

    List<RoleResponse> findAssignable();

    Optional<RoleResponse> findById(Integer id);

    RoleResponse save(RoleRequest request);

    RoleResponse update(Integer id, RoleRequest request);

    void delete(Integer id);

    List<UserResponse> findUsersByRoleId(Integer roleId);
}
