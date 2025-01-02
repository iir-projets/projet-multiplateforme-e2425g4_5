package ma.plantes.backend.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import ma.plantes.backend.entities.Favoris;
import ma.plantes.backend.entities.FavorisId;
import ma.plantes.backend.entities.Plante;
import ma.plantes.backend.entities.User;
import ma.plantes.backend.repositories.FavorisRepository;
import ma.plantes.backend.repositories.PlanteRepository;
import ma.plantes.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FavorisService {


    private final FavorisRepository favorisRepository;

    private final UserRepository userRepository;

    private final PlanteRepository planteRepository;


    // Récupérer les favoris d'un client spécifique
    public List<Favoris> getFavorisByClientId(Long clientId) {
        return favorisRepository.findByUser_Id(clientId);
    }


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

    @Transactional
    public boolean supprimerFavoris(Long clientId, Long planteId) {
        FavorisId favorisId = new FavorisId(clientId, planteId);

        if (favorisRepository.existsById(favorisId)) {
            favorisRepository.deleteById(favorisId);
            return true; // Return true if deletion was successful
        }
        return false; // Return false if the record does not exist
    }


    public List<Favoris> getAllFavoris() {
        return favorisRepository.findAll();
    }


    public Map<Long, Long> getTop5Plantes() {
        List<Object[]> results = favorisRepository.findTop5PlantesByFavoris();
        Map<Long, Long> topPlantes = new LinkedHashMap<>();

        for (Object[] result : results) {
            Long planteId = ((Number) result[0]).longValue();
            Long count = ((Number) result[1]).longValue();
            topPlantes.put(planteId, count);
        }

        return topPlantes;
    }
}
