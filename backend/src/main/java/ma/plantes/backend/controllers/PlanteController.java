package ma.plantes.backend.controllers;

import ma.plantes.backend.dto.PlanteDto;
import ma.plantes.backend.entities.Plante;
import ma.plantes.backend.service.PlanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class PlanteController {

    private final PlanteService planteService;

    @Autowired
    public PlanteController(PlanteService planteService) {
        this.planteService = planteService;
    }

    // Ajouter une plante
    @PostMapping("/admin/plantes")
    public ResponseEntity<Plante> ajouterPlante(@RequestBody PlanteDto planteDTO) {
        System.out.println("PlanteDTO received: " + planteDTO.toString());
        Plante newPlante = planteService.ajouterPlante(planteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPlante);
    }

    // Modifier une plante
    @PutMapping("/admin/plantes/{id}")
    public ResponseEntity<Plante> modifierPlante(@PathVariable Long id, @RequestBody Plante plante) {
        Plante updatedPlante = planteService.modifierPlante(id, plante);
        if (updatedPlante == null) {
            return ResponseEntity.notFound().build(); // Plante non trouvée pour modification
        }
        return ResponseEntity.ok(updatedPlante); // Plante modifiée avec succès
    }

    // Supprimer une plante
    @DeleteMapping("/admin/plantes/{id}")
    public ResponseEntity<Void> supprimerPlante(@PathVariable Long id) {
        boolean deleted = planteService.supprimerPlante(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // Plante supprimée
        }
        return ResponseEntity.notFound().build(); // Plante non trouvée pour suppression
    }

    // Afficher toutes les plantes
    @GetMapping("/plantes/")
    public List<Plante> afficherToutesLesPlantes() {
        return planteService.afficherToutesLesPlantes();
    }

    // Afficher une plante par ID
    @GetMapping("/plantes/{id}")
    public ResponseEntity<Plante> afficherPlanteParId(@PathVariable Long id) {
        Optional<Plante> plante = planteService.afficherPlanteParId(id);
        return plante.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build()); // Si la plante n'est pas trouvée, retourne 404
    }

    @GetMapping("/admin/plantes/total")
    public ResponseEntity<Long> getTotalCount() {
        return ResponseEntity.ok(planteService.getTotalCount());
    }


    @GetMapping("admin/plantes/totals-by-region")
    public Map<String, Long> getPlantesCountByRegion() {
        return planteService.getPlantesCountByRegion();
    }


}
