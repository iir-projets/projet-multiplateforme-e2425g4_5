package ma.plantes.backend.controllers;

import ma.plantes.backend.entities.Medicament;
import ma.plantes.backend.service.MedicamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MedicamentController {

    @Autowired
    private MedicamentService medicamentService;


    @PostMapping("sante/medicamants/add")
    public ResponseEntity<String> AjouterMedicament(@RequestBody Medicament medicament){
        try{
            if( medicamentService.existsByNom(medicament.getNom())){
                return ResponseEntity.status(400).body("Un médicament avec ce nom existe déjà !");
            }
            else{
                medicamentService.ajouterMedicament(medicament);
                return ResponseEntity.ok("Médicament ajouté avec succès !");
            }

        }catch(Exception e){
            return ResponseEntity.status(500).body("Erreur lors de l'ajout de l'allergie : " + e.getMessage());
        }
    }

    @DeleteMapping("sante/medicaments/delete/{id}")
    public ResponseEntity<String> SupprimerMedicament(@RequestParam Long id){
        try{
            if( medicamentService.existsById(id)){
                medicamentService.supprimerMedicament(id);
                return ResponseEntity.ok("Médicament supprimé avec succès !");
            }
            else{
                return ResponseEntity.status(400).body("Erreur : Le médicament avec l'ID \" + id + \" n'existe pas !");
            }
        }catch(Exception e){
            return ResponseEntity.status(500).body("Erreur lors de la suppression de l'allergie : " + e.getMessage());
        }
    }

    @GetMapping("medicaments/getall")
    public List<Medicament> AfficherMedicament(){
        return medicamentService.getAllMedicament();
    }
}
