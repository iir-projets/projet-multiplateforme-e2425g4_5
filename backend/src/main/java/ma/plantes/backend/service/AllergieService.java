package ma.plantes.backend.service;

import ma.plantes.backend.entities.Allergie;
import ma.plantes.backend.repositories.AllergieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AllergieService {

    @Autowired
    private AllergieRepository allergieRepository;

   public void ajouterAllergie(Allergie allergie){
       allergieRepository.save(allergie);
   }

   public boolean supprimerAllergie(Long id){
       if (allergieRepository.existsById(id)){
           allergieRepository.deleteById(id);
           return true;
       }
       return false;
   }
}
