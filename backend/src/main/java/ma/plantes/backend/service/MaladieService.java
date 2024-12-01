package ma.plantes.backend.service;

import lombok.RequiredArgsConstructor;
import ma.plantes.backend.entities.Maladie;
import ma.plantes.backend.repositories.MaladieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaladieService {


    private final MaladieRepository maladieRepository;

    public void ajouterMaladie(Maladie maladie){
        maladieRepository.save(maladie);
    }

    public boolean supprimerMaladie(Long id){
        if (maladieRepository.existsById(id)){
            maladieRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean existsByNom(String nom){
        if (maladieRepository.existsByNom(nom)){
            return true;
        }
        return false;
    }

    public boolean existsById(Long id){
        if(maladieRepository.existsById(id)){
            return true;
        }
        return false;
    }

    public List<Maladie> getAllMaladie(){
        return maladieRepository.findAll();
    }
}
