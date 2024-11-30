package ma.plantes.backend.service;

import ma.plantes.backend.entities.Medicament;
import ma.plantes.backend.repositories.MedicamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicamentService {

    @Autowired
    private MedicamentRepository medicamentRepository;

    public void ajouterMedicament(Medicament medicament){
        medicamentRepository.save(medicament);
    }

    public boolean supprimerMedicament(Long id){
        if (medicamentRepository.existsById(id)){
            medicamentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
