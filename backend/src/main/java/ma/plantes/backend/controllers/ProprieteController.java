package ma.plantes.backend.controllers;

import ma.plantes.backend.entities.Propriete;
import ma.plantes.backend.service.ProprieteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/proprietes")
public class ProprieteController {

    private final ProprieteService proprieteService;

    @Autowired
    public ProprieteController(ProprieteService proprieteService) {
        this.proprieteService = proprieteService;
    }

    // Ajouter une propriété
    @PostMapping
    public ResponseEntity<Propriete> ajouterPropriete(@RequestBody Propriete propriete) {
        Propriete nouvellePropriete = proprieteService.ajouterPropriete(propriete);
        if (nouvellePropriete != null) {
            return ResponseEntity.ok(nouvellePropriete);
        }
        return ResponseEntity.status(400).body(null);  // Retourner 400 si la propriété existe déjà
    }

    // Supprimer une propriété par son ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> supprimerPropriete(@PathVariable Long id) {
        boolean estSupprime = proprieteService.supprimerPropriete(id);
        if (estSupprime) {
            return ResponseEntity.ok("Propriété supprimée avec succès.");
        }
        return ResponseEntity.status(404).body("Propriété non trouvée.");
    }
}
