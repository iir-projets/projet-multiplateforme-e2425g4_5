package ma.plantes.backend.service;

import lombok.RequiredArgsConstructor;
import ma.plantes.backend.entities.Medicament;
import ma.plantes.backend.repositories.MedicamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicamentService {


    private final MedicamentRepository medicamentRepository;

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

    public boolean existsByNom(String nom){
        if( medicamentRepository.findByNom(nom)){
            return true;
        }
        return false;
    }

    public boolean existsById(Long id){
        if( medicamentRepository.existsById(id)){
            return true;
        }
        return false;
    }

    public List<Medicament> getAllMedicament(){
        return medicamentRepository.findAll();
    }
}
