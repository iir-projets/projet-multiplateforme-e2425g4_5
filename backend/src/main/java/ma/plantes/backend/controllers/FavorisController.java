package ma.plantes.backend.controllers;

import lombok.RequiredArgsConstructor;
import ma.plantes.backend.entities.Favoris;
import ma.plantes.backend.service.FavorisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class FavorisController {


    private final FavorisService favorisService;

    @PostMapping("/favoris/ajouter/{clientId}/{planteId}")
    public ResponseEntity<String> AjouterFavoris(@PathVariable Long clientId, @PathVariable Long planteId){
        try{
            favorisService.ajouterFavoris(clientId,planteId);
            return ResponseEntity.ok("Favori ajouté avec succès !");
        }catch (Exception e){
            return ResponseEntity.status(500).body("Erreur lors de l'ajout du favoris : "+ e.getMessage());
        }
    }

    @DeleteMapping("/favoris/supprimer")
    public ResponseEntity<String> RetirerFavoris(@RequestParam Long clientId, @RequestParam Long planteId){
        boolean supprime = favorisService.supprimerFavoris(clientId,planteId);
        if(supprime){
            return ResponseEntity.ok("Favori supprime avec succes !");
        }else{
            return ResponseEntity.status(500).body("Favori introuvable !");
        }
    }
}
