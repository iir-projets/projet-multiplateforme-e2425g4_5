package ma.plantes.backend.service;

import ma.plantes.backend.entities.Favoris;
import ma.plantes.backend.entities.FavorisId;
import ma.plantes.backend.repositories.FavorisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavorisService {

    @Autowired
    private FavorisRepository favorisRepository;

    public void ajouterFavoris(Favoris favoris){
        favorisRepository.save(favoris);
    }

    public boolean supprimerFavoris(Long clientId,Long planteId){
        FavorisId favorisId = new FavorisId(clientId,planteId);
        if(favorisRepository.existsById(favorisId)){
            favorisRepository.deleteById(favorisId);
            return true;
        }
        return false;
    }

}
