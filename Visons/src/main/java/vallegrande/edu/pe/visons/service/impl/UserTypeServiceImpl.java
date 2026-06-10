package vallegrande.edu.pe.visons.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import vallegrande.edu.pe.visons.dto.UserTypeResponse;
import vallegrande.edu.pe.visons.model.UserType;
import vallegrande.edu.pe.visons.repository.UserTypeRepository;
import vallegrande.edu.pe.visons.service.UserTypeService;

@Service
public class UserTypeServiceImpl implements UserTypeService {

    private final UserTypeRepository userTypeRepository;

    public UserTypeServiceImpl(UserTypeRepository userTypeRepository) {
        this.userTypeRepository = userTypeRepository;
    }

    @Override
    public List<UserTypeResponse> findAll() {
        return userTypeRepository.findAll().stream()
                .map(this::toResponse)
                .sorted(Comparator.comparing(UserTypeResponse::getName, String.CASE_INSENSITIVE_ORDER))
                .toList();
    }

    @Override
    public Optional<UserTypeResponse> findById(Integer id) {
        return userTypeRepository.findById(id).map(this::toResponse);
    }

    private UserTypeResponse toResponse(UserType userType) {
        UserTypeResponse response = new UserTypeResponse();
        response.setId(userType.getId());
        response.setName(userType.getName());
        return response;
    }
}
