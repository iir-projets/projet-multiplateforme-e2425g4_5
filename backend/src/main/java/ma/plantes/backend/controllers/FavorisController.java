package ma.plantes.backend.controllers;

import lombok.RequiredArgsConstructor;
import ma.plantes.backend.entities.Favoris;
import ma.plantes.backend.service.FavorisService;
import ma.plantes.backend.entities.Plante;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/favoris")
public class FavorisController {

    private final FavorisService favorisService;

    // Ajouter un favori
    @PostMapping("/ajouter/{clientId}/{planteId}")
    public ResponseEntity<String> ajouterFavoris(@PathVariable Long clientId, @PathVariable Long planteId) {
        try {
            favorisService.ajouterFavoris(clientId, planteId);
            return ResponseEntity.ok("Favori ajouté avec succès !");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur lors de l'ajout du favori : " + e.getMessage());
        }
    }

    // Supprimer un favori
    @DeleteMapping("/supprimer/{clientId}/{planteId}")
    public ResponseEntity<String> retirerFavoris(@PathVariable Long clientId, @PathVariable Long planteId) {
        boolean supprime = favorisService.supprimerFavoris(clientId, planteId);
        if (supprime) {
            return ResponseEntity.ok("Favori supprimé avec succès !");
        } else {
            return ResponseEntity.status(500).body("Favori introuvable !");
        }
    }

    // Afficher tous les favoris
    @GetMapping("/afficher")
    public List<Favoris> afficherTousLesFavoris() {
        return favorisService.getAllFavoris();
    }

    // Afficher les top 5 plantes
    @GetMapping("/top5")
    public Map<Long, Long> getTop5Plantes() {
        return favorisService.getTop5Plantes();
    }

}
