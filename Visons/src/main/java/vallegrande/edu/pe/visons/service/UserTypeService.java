package vallegrande.edu.pe.visons.service;

import java.util.List;
import java.util.Optional;

import vallegrande.edu.pe.visons.dto.UserTypeResponse;

public interface UserTypeService {

    List<UserTypeResponse> findAll();

    Optional<UserTypeResponse> findById(Integer id);
}
