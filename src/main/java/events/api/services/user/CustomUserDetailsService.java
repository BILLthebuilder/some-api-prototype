package events.api.services.user;

import lombok.AllArgsConstructor;
import events.api.helpers.CustomUserDetails;
import events.api.exceptions.ResourceNotFoundException;
import events.api.repositories.UserRepository;
import events.api.model.user.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(s);
        if (user == null) {
            throw new ResourceNotFoundException("User not found with email " + s);
        }
        return new CustomUserDetails(user);
    }
}