package vallegrande.edu.pe.visons.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vallegrande.edu.pe.visons.dto.UserTypeResponse;
import vallegrande.edu.pe.visons.service.UserTypeService;

@RestController
@RequestMapping("/v1/api/user-types")
public class UserTypeRest {

    private final UserTypeService userTypeService;

    public UserTypeRest(UserTypeService userTypeService) {
        this.userTypeService = userTypeService;
    }

    @GetMapping({"", "/"})
    public List<UserTypeResponse> findAll() {
        return userTypeService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<UserTypeResponse> findById(@PathVariable Integer id) {
        return userTypeService.findById(id);
    }
}
