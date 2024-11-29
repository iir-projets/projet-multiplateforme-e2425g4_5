package ma.plantes.backend.service;

import ma.plantes.backend.entities.Maladie;
import ma.plantes.backend.repositories.MaladieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaladieService {

    @Autowired
    private MaladieRepository maladieRepository;

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
}
