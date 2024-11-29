package ma.plantes.backend.service;

import lombok.RequiredArgsConstructor;
import ma.plantes.backend.entities.Maladie;
import ma.plantes.backend.entities.Role;
import ma.plantes.backend.entities.User;
import ma.plantes.backend.repositories.UserRepository;
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

    public List<Maladie> addMaladie(Long userId, Long maladieId){

    }


}
