package vallegrande.edu.pe.visons.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import vallegrande.edu.pe.visons.model.Worker;
import vallegrande.edu.pe.visons.repository.WorkerRepository;
import vallegrande.edu.pe.visons.service.WorkerService;

@Slf4j
@Service
public class WorkerServiceImpl implements WorkerService {

    private final WorkerRepository workerRepository;

    @Autowired
    public WorkerServiceImpl(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    @Override
    public List<Worker> findAll() {
        return workerRepository.findByIsActive(Boolean.TRUE);
    }

    @Override
    public List<Worker> findByState(String state) {
        boolean active = "A".equalsIgnoreCase(state) || "1".equals(state) || "true".equalsIgnoreCase(state);
        return workerRepository.findByIsActive(active);
    }

    @Override
    public List<Worker> findByUbigeo(Integer ubigeoId) {
        return workerRepository.findByUbigeoIdAndIsActive(ubigeoId, Boolean.TRUE);
    }

    @Override
    public Optional<Worker> findById(Integer id) {
        return workerRepository.findById(id);
    }

    @Override
    public Worker save(Worker worker) {
        LocalDateTime now = LocalDateTime.now();
        worker.setWorkerId(null);
        worker.setIsActive(true);
        worker.setCreatedAt(now);
        worker.setUpdatedAt(null);
        worker.setDeletedAt(null);
        worker.setRestoredAt(null);
        return workerRepository.save(worker);
    }

    @Override
    public Worker update(Integer id, Worker workerDetails) {
        Optional<Worker> existingWorker = workerRepository.findById(id);
        if (existingWorker.isPresent()) {
            Worker worker = existingWorker.get();
            if (workerDetails.getFirstName() != null) worker.setFirstName(workerDetails.getFirstName());
            if (workerDetails.getLastName() != null) worker.setLastName(workerDetails.getLastName());
            if (workerDetails.getPhone() != null) worker.setPhone(workerDetails.getPhone());
            if (workerDetails.getEmail() != null) worker.setEmail(workerDetails.getEmail());
            if (workerDetails.getAddress() != null) worker.setAddress(workerDetails.getAddress());
            if (workerDetails.getUbigeoId() != null) worker.setUbigeoId(workerDetails.getUbigeoId());
            if (workerDetails.getDocumentType() != null) worker.setDocumentType(workerDetails.getDocumentType());
            if (workerDetails.getDocumentNumber() != null) worker.setDocumentNumber(workerDetails.getDocumentNumber());
            worker.setUpdatedAt(LocalDateTime.now());
            return workerRepository.save(worker);
        }
        throw new RuntimeException("Worker not found");
    }

    @Override
    public Worker delete(Integer id) {
        Optional<Worker> existingWorker = workerRepository.findById(id);
        if (existingWorker.isPresent()) {
            Worker worker = existingWorker.get();
            worker.setIsActive(false);
            worker.setDeletedAt(LocalDateTime.now());
            return workerRepository.save(worker);
        }
        throw new RuntimeException("Worker not found");
    }

    @Override
    public Worker restore(Integer id) {
        Optional<Worker> existingWorker = workerRepository.findById(id);
        if (existingWorker.isPresent()) {
            Worker worker = existingWorker.get();
            worker.setIsActive(true);
            worker.setRestoredAt(LocalDateTime.now());
            return workerRepository.save(worker);
        }
        throw new RuntimeException("Worker not found");
    }
}
