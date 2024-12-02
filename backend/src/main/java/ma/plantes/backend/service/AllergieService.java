package ma.plantes.backend.service;

import lombok.RequiredArgsConstructor;
import ma.plantes.backend.entities.Allergie;
import ma.plantes.backend.repositories.AllergieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AllergieService {


    private final AllergieRepository allergieRepository;

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

   public boolean existsByName(String nom){
       if (allergieRepository.existsByNom(nom)){
           return true;
       }
       return false;
   }

    public boolean exsistById(Long id){
       if(allergieRepository.existsById(id)){
           return true;
       }
       return false;
    }

    public List<Allergie> getAllAllergie(){
       return allergieRepository.findAll();
    }
}
