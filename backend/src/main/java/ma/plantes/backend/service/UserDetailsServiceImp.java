package ma.plantes.backend.service;

import lombok.RequiredArgsConstructor;
import ma.plantes.backend.entities.*;
import ma.plantes.backend.repositories.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {

    private final UserRepository userRepository;
    private final MaladieRepository maladieRepository;
    private final ClientMaladieRepository clientMaladieRepository;
    private final ClientMedicamentRepository clientMedicamentRepository;
    private final ClientAllergieRepository clientAllergieRepository;
    private final AllergieRepository allergieRepository;
    private final MedicamentRepository medicamentRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("UserName "+username+" not found"));
    }

    public List<User> allClients(){
        return userRepository.findUsersByRole(Role.CLIENT);
    }
    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    public List<ClientMaladie> addMaladie(Long userId, Long maladieId){

        Maladie maladie = maladieRepository.findById(maladieId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();

        ClientMaladieId clientMaladieId = new ClientMaladieId(userId,maladieId);
        ClientMaladie clientMaladie = new ClientMaladie(clientMaladieId,user,maladie);

        user.getMaladies().add(clientMaladie);
        maladie.getUsers().add(clientMaladie);

        clientMaladieRepository.save(clientMaladie);
        userRepository.save(user);
        maladieRepository.save(maladie);

        return clientMaladieRepository.findClientMaladiesByUser(user);
    }

    public List<ClientMaladie> deleteMaladie(Long userId, Long maladieId){

        Maladie maladie = maladieRepository.findById(maladieId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();


        ClientMaladie clientMaladie = clientMaladieRepository.findClientMaladieByUserAndMaladie(user,maladie).orElseThrow();

        user.getMaladies().remove(clientMaladie);
        maladie.getUsers().remove(clientMaladie);

        clientMaladieRepository.delete(clientMaladie);
        userRepository.save(user);
        maladieRepository.save(maladie);

        return clientMaladieRepository.findClientMaladiesByUser(user);
    }
///////////////////////////////////////////////////////////////////
public List<ClientMedicament> addMedicament(Long userId, Long medicamentId){

    Medicament medicament = medicamentRepository.findById(medicamentId).orElseThrow();
    User user = userRepository.findById(userId).orElseThrow();

    ClientMedicamentId clientMedicamentId = new ClientMedicamentId(userId,medicamentId);
    ClientMedicament clientMedicament = new ClientMedicament(clientMedicamentId,user,medicament);

    user.getMedicaments().add(clientMedicament);
    medicament.getUsers().add(clientMedicament);

    clientMedicamentRepository.save(clientMedicament);
    userRepository.save(user);
    medicamentRepository.save(medicament);

    return clientMedicamentRepository.findClientMedicamentsByUser(user);
}

    public List<ClientMedicament> deleteMedicament(Long userId, Long medicamentId){

        Medicament medicament = medicamentRepository.findById(medicamentId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();


        ClientMedicament clientMedicament = clientMedicamentRepository.findClientMedicamentByUserAndMedicament(user,medicament).orElseThrow();

        user.getMaladies().remove(clientMedicament);
        medicament.getUsers().remove(clientMedicament);

        clientMedicamentRepository.delete(clientMedicament);
        userRepository.save(user);
        medicamentRepository.save(medicament);

        return clientMedicamentRepository.findClientMedicamentsByUser(user);
    }
    ///////////////////////////////////////////////////////////////////
    public List<ClientAllergie> addAllergie(Long userId, Long allergieId){

        Allergie allergie = allergieRepository.findById(allergieId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();

        ClientAllergieId clientAllergieId = new ClientAllergieId(userId,allergieId);
        ClientAllergie clientAllergie = new ClientAllergie(clientAllergieId,user,allergie);

        user.getAllergies().add(clientAllergie);
        allergie.getUsers().add(clientAllergie);

        clientAllergieRepository.save(clientAllergie);
        userRepository.save(user);
        allergieRepository.save(allergie);

        return clientAllergieRepository.findClientAllergiesByUser(user);
    }

    public List<ClientAllergie> deleteAllergie(Long userId, Long allergieId){

        Allergie allergie = allergieRepository.findById(allergieId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();


        ClientAllergie clientAllergie = clientAllergieRepository.findClientAllergieByUserAndAllergie(user,allergie).orElseThrow();

        user.getMaladies().remove(clientAllergie);
        allergie.getUsers().remove(clientAllergie);

        clientAllergieRepository.delete(clientAllergie);
        userRepository.save(user);
        allergieRepository.save(allergie);

        return clientAllergieRepository.findClientAllergiesByUser(user);
    }

    // Modifier client profil :
}
