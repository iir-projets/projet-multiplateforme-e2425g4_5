package ma.plantes.backend.controllers;

import lombok.RequiredArgsConstructor;
import ma.plantes.backend.entities.Allergie;
import ma.plantes.backend.repositories.AllergieRepository;
import ma.plantes.backend.service.AllergieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AllergieController {


    private final AllergieService allergieService;


    @PostMapping("/sante/allergies/add")
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

    @DeleteMapping("/sante/allergies/delete/{id}")
    public ResponseEntity<String> SupprimerAllergie(@PathVariable Long id){
        try{
            if(allergieService.exsistById(id)){
                allergieService.supprimerAllergie(id);
                return ResponseEntity.ok("Allergie supprimée avec succès !");
            }
            else{
                return ResponseEntity.status(400).body("Erreur : L'allergie avec l'ID \" + id + \" n'existe pas !");
            }
        }catch(Exception e){
            return ResponseEntity.status(500).body("Erreur lors de la suppression de l'allergie : " + e.getMessage());
        }
    }

    @GetMapping("/allergies/getall")
    public List<Allergie> AfficherAllergie(){
        return allergieService.getAllAllergie();
    }


}
