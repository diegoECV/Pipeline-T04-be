package vallegrande.edu.pe.visons.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vallegrande.edu.pe.visons.dto.RoleRequest;
import vallegrande.edu.pe.visons.dto.RoleResponse;
import vallegrande.edu.pe.visons.dto.UserResponse;
import vallegrande.edu.pe.visons.model.Role;
import vallegrande.edu.pe.visons.repository.RoleRepository;
import vallegrande.edu.pe.visons.repository.UserAccountRepository;
import vallegrande.edu.pe.visons.repository.UserRoleRepository;
import vallegrande.edu.pe.visons.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final UserAccountRepository userAccountRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserServiceImpl userService;

    public RoleServiceImpl(RoleRepository roleRepository, UserAccountRepository userAccountRepository,
            UserRoleRepository userRoleRepository, UserServiceImpl userService) {
        this.roleRepository = roleRepository;
        this.userAccountRepository = userAccountRepository;
        this.userRoleRepository = userRoleRepository;
        this.userService = userService;
    }

    @Override
    public List<RoleResponse> findAll() {
        ensureAdminRole();
        return roleRepository.findAll().stream().map(this::toRoleResponse)
                .sorted(Comparator.comparing(RoleResponse::getName, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
    }

    @Override
    public List<RoleResponse> findAssignable() {
        return findAll().stream()
                .filter(RoleResponse::getAssignable)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RoleResponse> findById(Integer id) {
        return roleRepository.findById(id).map(this::toRoleResponse);
    }

    @Transactional
    @Override
    public RoleResponse save(RoleRequest request) {
        validateRoleRequest(request);
        if (roleRepository.findByName(request.getName().trim()).isPresent()) {
            throw new RuntimeException("Role already exists");
        }

        Role role = new Role();
        role.setName(request.getName().trim());
        role.setDescription(request.getDescription());
        return toRoleResponse(roleRepository.save(role));
    }

    @Transactional
    @Override
    public RoleResponse update(Integer id, RoleRequest request) {
        validateRoleRequest(request);
        Role existing = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with ID: " + id));

        Optional<Role> duplicate = roleRepository.findByName(request.getName().trim());
        if (duplicate.isPresent() && !duplicate.get().getRoleId().equals(id)) {
            throw new RuntimeException("Role already exists");
        }

        existing.setName(request.getName().trim());
        existing.setDescription(request.getDescription());
        return toRoleResponse(roleRepository.save(existing));
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Role existing = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with ID: " + id));

        userRoleRepository.removeAssignmentsByRoleId(existing.getRoleId());
        roleRepository.delete(existing);
    }

    @Override
    public List<UserResponse> findUsersByRoleId(Integer roleId) {
        List<Integer> userIds = userRoleRepository.findUserIdsByRoleId(roleId);
        if (userIds.isEmpty()) {
            return List.of();
        }
        return userIds.stream().map(userService::findById).flatMap(Optional::stream).collect(Collectors.toList());
    }

    private void validateRoleRequest(RoleRequest request) {
        if (request == null || request.getName() == null || request.getName().isBlank()) {
            throw new RuntimeException("Role name is required");
        }
    }

    private void ensureAdminRole() {
        boolean existsAdmin = roleRepository.findAll().stream()
                .anyMatch(role -> role.getName() != null && "ADMIN".equalsIgnoreCase(role.getName().trim()));

        if (!existsAdmin) {
            Role admin = new Role();
            admin.setName("ADMIN");
            admin.setDescription("Acceso completo al panel");
            roleRepository.save(admin);
        }
    }

    private RoleResponse toRoleResponse(Role role) {
        RoleResponse response = new RoleResponse();
        response.setRoleId(role.getRoleId());
        response.setName(role.getName());
        response.setDescription(role.getDescription());
        response.setUserCount(userRoleRepository.countUsersByRoleId(role.getRoleId()));
        response.setAssignable(role.getName() != null && !"ADMIN".equalsIgnoreCase(role.getName().trim()));
        return response;
    }
}
