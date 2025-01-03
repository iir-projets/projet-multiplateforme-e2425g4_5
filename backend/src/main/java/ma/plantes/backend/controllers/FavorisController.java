package ma.plantes.backend.controllers;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import ma.plantes.backend.entities.Favoris;
import ma.plantes.backend.service.FavorisService;
import ma.plantes.backend.entities.Plante;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class FavorisController {

    private final FavorisService favorisService;



    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Map<String, Object>>> getFavorisByClient(@PathVariable Long clientId) {
        List<Favoris> favoris = favorisService.getFavorisByClientId(clientId);

        // Transformer la réponse pour inclure les détails des plantes
        List<Map<String, Object>> response = favoris.stream().map(favori -> {
            Map<String, Object> plantDetails = Map.of(
                    "id", favori.getPlante().getId(),
                    "name", favori.getPlante().getName(),
                    "description", favori.getPlante().getDescription(),
                    "image", favori.getPlante().getImage()
            );
            return plantDetails;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    // Ajouter un favori
    @PostMapping("/favoris/ajouter/{clientId}/{planteId}")
    public ResponseEntity<String> ajouterFavoris(@PathVariable Long clientId, @PathVariable Long planteId) {
        try {
            favorisService.ajouterFavoris(clientId, planteId);
            return ResponseEntity.ok("Favori ajouté avec succès !");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur lors de l'ajout du favori : " + e.getMessage());
        }
    }

    // Supprimer un favori
    @DeleteMapping("/favoris/supprimer/{clientId}/{planteId}")
    public ResponseEntity<String> retirerFavoris(@PathVariable Long clientId, @PathVariable Long planteId) {
        boolean supprime = favorisService.supprimerFavoris(clientId, planteId);
        if (supprime) {
            return ResponseEntity.ok("Favori supprimé avec succès !");
        } else {
            return ResponseEntity.status(500).body("Favori introuvable !");
        }
    }


    // Afficher tous les favoris du client
    @GetMapping("/favoris/afficher")
    public List<Favoris> afficherTousLesFavoris() {
        return favorisService.getAllFavoris();
    }

    // Afficher tous les favoris
    @GetMapping("/admin/favoris/clientfavoris/{clientId}")
    public List<Favoris> afficherFavorisByClient(@PathVariable Long clientId) {
        return favorisService.getAllFavorisByClient(clientId);
    }


    // Afficher les top 5 plantes
    @GetMapping("/admin/favoris/top5")
    public Map<Long, Long> getTop5Plantes() {
        return favorisService.getTop5Plantes();
    }

}
