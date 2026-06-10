package vallegrande.edu.pe.visons.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import vallegrande.edu.pe.visons.model.Worker;
import vallegrande.edu.pe.visons.service.WorkerService;

@RestController
@RequestMapping("/v1/api/worker")
@Tag(name = "Worker API", description = "API for Worker (maestro) management")
public class WorkerRest {

    private final WorkerService workerService;

    @Autowired
    public WorkerRest(WorkerService workerService) {
        this.workerService = workerService;
    }

    @GetMapping({"", "/"})
    @Operation(summary = "Get All Workers", description = "Get All Workers")
    public List<Worker> findAll() {
        return workerService.findAll();
    }

    @GetMapping("/state/{state}")
    @Operation(summary = "Get Worker By STATE (A/1/true for active)", description = "Get Worker By STATE")
    public List<Worker> findByState(@PathVariable String state) {
        return workerService.findByState(state);
    }

    @GetMapping("/ubigeo/{ubigeoId}")
    @Operation(summary = "Get Workers By Ubigeo", description = "Get active workers assigned to a ubigeo")
    public List<Worker> findByUbigeo(@PathVariable Integer ubigeoId) {
        return workerService.findByUbigeo(ubigeoId);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Worker By ID", description = "Get Worker By ID")
    public Optional<Worker> findById(@PathVariable Integer id) {
        return workerService.findById(id);
    }

    @PostMapping("/save")
    @Operation(summary = "Crear (POST) - (fecha-hora)", description = "Save Worker")
    public Worker save(@RequestBody Worker worker) {
        return workerService.save(worker);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Editar (PUT) - (fecha-hora)", description = "Update Worker")
    public Worker update(@PathVariable Integer id, @RequestBody Worker worker) {
        return workerService.update(id, worker);
    }
    @PatchMapping("/{id}")
    @Operation(summary = "Eliminar (lógico) (PATCH) - (fecha-hora)", description = "Logical Delete Worker")
    public Worker delete(@PathVariable Integer id) {
        return workerService.delete(id);
    }

    @PatchMapping("/restore/{id}")
    @Operation(summary = "Restaurar (lógico) (PATCH) - (fecha-hora).", description = "Logical Restore Worker")
    public Worker restore(@PathVariable Integer id) {
        return workerService.restore(id);
    }
}
