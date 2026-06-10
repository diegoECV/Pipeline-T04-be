package vallegrande.edu.pe.visons.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vallegrande.edu.pe.visons.model.Worker;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Integer> {
	java.util.List<Worker> findByIsActive(Boolean isActive);

	java.util.List<Worker> findByUbigeoIdAndIsActive(Integer ubigeoId, Boolean isActive);

	Optional<Worker> findByDocumentNumber(String documentNumber);

	Optional<Worker> findByEmailIgnoreCase(String email);
}
