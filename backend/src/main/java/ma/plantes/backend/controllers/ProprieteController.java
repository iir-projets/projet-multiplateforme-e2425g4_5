package ma.plantes.backend.controllers;

import ma.plantes.backend.entities.Propriete;
import ma.plantes.backend.service.ProprieteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/proprietes")
public class ProprieteController {

    private final ProprieteService proprieteService;

    @Autowired
    public ProprieteController(ProprieteService proprieteService) {
        this.proprieteService = proprieteService;
    }

    @GetMapping
    public List<Propriete> getAllProprietes() {
        return proprieteService.getAllProprietes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Propriete> getProprieteById(@PathVariable Long id) {
        Optional<Propriete> propriete = proprieteService.getProprieteById(id);
        return propriete.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/nom/{nom}")
    public List<Propriete> getProprietesByNom(@PathVariable String nom) {
        return proprieteService.getProprietesByNom(nom);
    }

    @GetMapping("/plante/{planteId}")
    public List<Propriete> getProprietesByPlanteId(@PathVariable Long planteId) {
        return proprieteService.getProprietesByPlanteId(planteId);
    }

    @PostMapping
    public ResponseEntity<Propriete> createPropriete(@RequestBody Propriete propriete) {
        Propriete createdPropriete = proprieteService.createPropriete(propriete);
        return ResponseEntity.status(201).body(createdPropriete);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Propriete> updatePropriete(@PathVariable Long id, @RequestBody Propriete propriete) {
        Propriete updatedPropriete = proprieteService.updatePropriete(id, propriete);
        return updatedPropriete != null ? ResponseEntity.ok(updatedPropriete) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePropriete(@PathVariable Long id) {
        proprieteService.deletePropriete(id);
        return ResponseEntity.noContent().build();
    }
}
