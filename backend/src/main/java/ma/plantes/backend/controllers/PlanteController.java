package ma.plantes.backend.controllers;

import ma.plantes.backend.entities.Plante;
import ma.plantes.backend.service.PlanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/plantes")
public class PlanteController {

    private final PlanteService planteService;

    @Autowired
    public PlanteController(PlanteService planteService) {
        this.planteService = planteService;
    }

    @GetMapping
    public List<Plante> getAllPlantes() {
        return planteService.getAllPlantes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plante> getPlanteById(@PathVariable Long id) {
        Optional<Plante> plante = planteService.getPlanteById(id);
        return plante.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/nom/{nom}")
    public List<Plante> getPlantesByNom(@PathVariable String nom) {
        return planteService.getPlantesByNom(nom);
    }

    @GetMapping("/region/{region}")
    public List<Plante> getPlantesByRegion(@PathVariable String region) {
        return planteService.getPlantesByRegion(region);
    }

    @PostMapping
    public ResponseEntity<Plante> createPlante(@RequestBody Plante plante) {
        Plante createdPlante = planteService.createPlante(plante);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPlante);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plante> updatePlante(@PathVariable Long id, @RequestBody Plante plante) {
        Plante updatedPlante = planteService.updatePlante(id, plante);
        return updatedPlante != null ? ResponseEntity.ok(updatedPlante) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlante(@PathVariable Long id) {
        planteService.deletePlante(id);
        return ResponseEntity.noContent().build();
    }
}
