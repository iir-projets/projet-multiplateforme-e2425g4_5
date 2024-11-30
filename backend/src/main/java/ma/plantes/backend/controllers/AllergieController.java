package ma.plantes.backend.controllers;

import ma.plantes.backend.entities.Allergie;
import ma.plantes.backend.repositories.AllergieRepository;
import ma.plantes.backend.service.AllergieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AllergieController {

    @Autowired
    private AllergieService allergieService;
    @Autowired
    private AllergieRepository allergieRepository;

    @PostMapping("sante/allergies/ajouter")
    public ResponseEntity<String> AjouterAllergie(@RequestBody Allergie allergie){
        try{
            if(allergieService.existsByName(allergie.getNom())){
                return ResponseEntity.status(400).body("Une allergie avec ce nom existe déjà !");
            }
            allergieService.ajouterAllergie(allergie);
            return ResponseEntity.ok("Allergie ajoutée avec succès !");
        }catch(Exception e){
            return ResponseEntity.status(500).body("Erreur lors de l'ajout de l'allergie : " + e.getMessage());
        }
    }

    /*@DeleteMapping("/delete")
    public ResponseEntity<String> SupprimerAllergie(@RequestBody Long id){
        try{
            if(allergieRepository.){
                return ResponseEntity.status(400).body("Une allergie avec ce nom existe déjà !");
            }
            allergieRepository.findById(id);
            allergieService.supprimerAllergie(id);
            return ResponseEntity.ok("Allergie ajoutée avec succès !");
        }catch(Exception e){
            return ResponseEntity.status(500).body("Erreur lors de l'ajout de l'allergie : " + e.getMessage());
        }
    }*/


}
