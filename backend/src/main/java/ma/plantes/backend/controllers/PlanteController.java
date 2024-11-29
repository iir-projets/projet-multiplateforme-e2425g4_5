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

    // Ajouter une plante
    @PostMapping
    public ResponseEntity<Plante> ajouterPlante(@RequestBody Plante plante) {
        Plante newPlante = planteService.ajouterPlante(plante);
        return new ResponseEntity<>(newPlante, HttpStatus.CREATED);
    }

    // Modifier une plante
    @PutMapping("/{id}")
    public ResponseEntity<Plante> modifierPlante(@PathVariable Long id, @RequestBody Plante plante) {
        Plante updatedPlante = planteService.modifierPlante(id, plante);
        if (updatedPlante == null) {
            return ResponseEntity.notFound().build(); // Plante non trouvée pour modification
        }
        return ResponseEntity.ok(updatedPlante); // Plante modifiée avec succès
    }

    // Supprimer une plante
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerPlante(@PathVariable Long id) {
        boolean deleted = planteService.supprimerPlante(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // Plante supprimée
        }
        return ResponseEntity.notFound().build(); // Plante non trouvée pour suppression
    }

    // Afficher toutes les plantes
    @GetMapping
    public List<Plante> afficherToutesLesPlantes() {
        return planteService.afficherToutesLesPlantes();
    }

    // Afficher une plante par ID
    @GetMapping("/{id}")
    public ResponseEntity<Plante> afficherPlanteParId(@PathVariable Long id) {
        Optional<Plante> plante = planteService.afficherPlanteParId(id);
        return plante.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build()); // Si la plante n'est pas trouvée, retourne 404
    }
}
