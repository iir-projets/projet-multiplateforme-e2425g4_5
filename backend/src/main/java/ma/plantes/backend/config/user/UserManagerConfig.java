package ma.plantes.backend.config.user;

import lombok.RequiredArgsConstructor;
import ma.plantes.backend.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserManagerConfig implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .map(UserConfig::new)
                .orElseThrow(
                    ()-> new UsernameNotFoundException("UserName: "+ username+ " does not exist")
                );
    }

    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(email)
                .map(UserConfig::new)
                .orElseThrow(
                        ()-> new UsernameNotFoundException("UserEmail: "+ email+ " does not exist")
                );
    }
}
