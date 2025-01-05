package ma.plantes.backend.service;

import lombok.RequiredArgsConstructor;
import ma.plantes.backend.dto.FavorisDTO;
import ma.plantes.backend.entities.Favoris;
import ma.plantes.backend.entities.FavorisId;
import ma.plantes.backend.entities.Plante;
import ma.plantes.backend.entities.User;
import ma.plantes.backend.repositories.FavorisRepository;
import ma.plantes.backend.repositories.PlanteRepository;
import ma.plantes.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FavorisService {


    private final FavorisRepository favorisRepository;

    private final UserRepository userRepository;

    private final PlanteRepository planteRepository;

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

    public List<Favoris> getAllFavoris() {
        return favorisRepository.findAll();
    }

    // Method to get all favoris for a client
    public List<Favoris> getAllFavorisByClient(Long clientId) {
        return favorisRepository.findAllByUserId(clientId);
    }

    // Method to convert Favoris entities to DTOs
    public List<FavorisDTO> convertToFavorisDTO(List<Favoris> favorisList) {
        List<FavorisDTO> response = new ArrayList<>();
        for (Favoris favoris : favorisList) {
            FavorisDTO dto = new FavorisDTO(
                    favoris.getUser().getId(),
                    favoris.getUser().getUsername(),
                    favoris.getPlante().getId(),
                    favoris.getPlante().getNom()
            );
            response.add(dto);
        }
        return response;
    }

    public Map<String, Long> getTop5Plantes() {
        List<Object[]> results = favorisRepository.findTop5PlantesByFavoris();
        Map<String, Long> topPlantes = new LinkedHashMap<>();

        for (Object[] result : results) {
            String planteName = (String) result[1]; // nom_plante
            Long count = ((Number) result[2]).longValue(); // total
            topPlantes.put(planteName, count);
        }

        return topPlantes;
    }

}
