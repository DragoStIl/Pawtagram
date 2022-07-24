package project.services;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import project.entities.Role;
import project.entities.User;
import project.repositories.UserRepository;

public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public AppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(this::map)
                .orElseThrow(() ->new UsernameNotFoundException("No such user"));

    }

    private UserDetails map(User user){
    // because my user entity is named just User it is in conflict with the named operations with spring security
                return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(authorityMap(user.getRole())).build();
    }

    private GrantedAuthority authorityMap(Role role){
        return new SimpleGrantedAuthority("ROLE_" + role.getName().name());

    }


}
