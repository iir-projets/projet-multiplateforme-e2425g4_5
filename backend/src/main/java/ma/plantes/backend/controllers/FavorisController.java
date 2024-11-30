package ma.plantes.backend.controllers;

import ma.plantes.backend.entities.Favoris;
import ma.plantes.backend.service.FavorisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FavorisController {

    @Autowired
    private FavorisService favorisService;

    @PostMapping("favoris/ajouter")
    public ResponseEntity<String> AjouterFavoris(@RequestBody Favoris favoris){
        try{
            favorisService.ajouterFavoris(favoris);
            return ResponseEntity.ok("Favori ajouté avec succès !");
        }catch (Exception e){
            return ResponseEntity.status(500).body("Erreur lors de l'ajout du favoris : "+ e.getMessage());
        }
    }

    @DeleteMapping("/supprimer")
    public ResponseEntity<String> RetirerFavoris(@RequestParam Long clientId, @RequestParam Long planteId){
        boolean supprime = favorisService.supprimerFavoris(clientId,planteId);
        if(supprime){
            return ResponseEntity.ok("Favori supprime avec succes !");
        }else{
            return ResponseEntity.status(500).body("Favori introuvable !");
        }
    }
}
