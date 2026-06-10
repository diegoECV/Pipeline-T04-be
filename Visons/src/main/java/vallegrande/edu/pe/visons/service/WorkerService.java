package vallegrande.edu.pe.visons.service;

import java.util.List;
import java.util.Optional;

import vallegrande.edu.pe.visons.model.Worker;

public interface WorkerService {

    List<Worker> findAll();

    List<Worker> findByState(String state);

    List<Worker> findByUbigeo(Integer ubigeoId);

    Optional<Worker> findById(Integer id);

    Worker save(Worker worker);

    Worker update(Integer id, Worker worker);

    Worker delete(Integer id);

    Worker restore(Integer id);
}
