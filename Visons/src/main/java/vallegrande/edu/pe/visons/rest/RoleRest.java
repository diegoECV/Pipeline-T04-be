package vallegrande.edu.pe.visons.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.ResponseEntity;

import vallegrande.edu.pe.visons.dto.RoleRequest;
import vallegrande.edu.pe.visons.dto.RoleResponse;
import vallegrande.edu.pe.visons.dto.UserResponse;
import vallegrande.edu.pe.visons.service.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleRest {

    private final RoleService roleService;

    @Autowired
    public RoleRest(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping({"", "/"})
    public List<RoleResponse> findAll() {
        return roleService.findAll();
    }

    @GetMapping("/assignable")
    public List<RoleResponse> findAssignable() {
        return roleService.findAssignable();
    }

    @GetMapping("/{id}")
    public Optional<RoleResponse> findById(@PathVariable Integer id) {
        return roleService.findById(id);
    }

    @PostMapping
    public RoleResponse save(@RequestBody RoleRequest request) {
        return roleService.save(request);
    }

    @PutMapping("/{id}")
    public RoleResponse update(@PathVariable Integer id, @RequestBody RoleRequest request) {
        return roleService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        roleService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/users")
    public List<UserResponse> findUsersByRoleId(@PathVariable Integer id) {
        return roleService.findUsersByRoleId(id);
    }
}
