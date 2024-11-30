package ma.plantes.backend.controllers;

import ma.plantes.backend.entities.Maladie;
import ma.plantes.backend.service.MaladieService;
import ma.plantes.backend.service.MedicamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MaladieController {

    @Autowired
    private MaladieService maladieService;

    @PostMapping("sante/maladies/add")
    public ResponseEntity<String> AjouterMaladie(@RequestBody Maladie maladie){
        try{
            if( maladieService.existsByNom(maladie.getNom())){
                return ResponseEntity.status(400).body("Une maladie avec ce nom existe déjà !");
            }
            maladieService.ajouterMaladie(maladie);
            return ResponseEntity.ok("Maladie ajoutée avec succès !");
        }catch(Exception e){
            return ResponseEntity.status(500).body("Erreur lors de l'ajout de la maladie : " + e.getMessage());
        }
    }

    @DeleteMapping("sante/maladies/delete/{id}")
    public ResponseEntity<String> SupprimerMaladie(@RequestParam Long id){
        try{
            if( maladieService.existsById(id)){
                maladieService.supprimerMaladie(id);
                return ResponseEntity.ok("Maladie supprimée avec succès");
            }else{
                return ResponseEntity.status(400).body("Erreur : L'allergie avec l'ID \" + id + \" n'existe pas !");
            }
        }catch(Exception e){
            return ResponseEntity.status(500).body("Erreur lors de la suppression de la maladie : " + e.getMessage());
        }
    }

    @GetMapping("/maladies/getall")
    public List<Maladie> AfficherMaladie(){
        return maladieService.getAllMaladie();
    }
}
