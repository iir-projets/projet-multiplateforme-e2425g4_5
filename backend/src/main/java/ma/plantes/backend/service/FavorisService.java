package ma.plantes.backend.service;

import ma.plantes.backend.entities.Favoris;
import ma.plantes.backend.entities.FavorisId;
import ma.plantes.backend.entities.Plante;
import ma.plantes.backend.entities.User;
import ma.plantes.backend.repositories.FavorisRepository;
import ma.plantes.backend.repositories.PlanteRepository;
import ma.plantes.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavorisService {

    @Autowired
    private FavorisRepository favorisRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlanteRepository planteRepository;

    public void ajouterFavoris(Long clientId,Long planteId){

        // recuperation de l'id client
        User user = userRepository.findById(clientId).orElseThrow(()-> new RuntimeException("Client non trouvé avec cet id"+ clientId));

        // recuperation de l'id plante
        Plante plante = planteRepository.findById(planteId).orElseThrow(()-> new RuntimeException("Client non trouvé avec cet id"+ planteId));

        // creer un objet favoris et associer  client et plante
        Favoris favoris = new Favoris();
        FavorisId favorisId = new FavorisId(clientId,planteId);
        favoris.setId(favorisId);
        favoris.setUser(user);
        favoris.setPlante(plante);

        // sauvegarder favori
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
