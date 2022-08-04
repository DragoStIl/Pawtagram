package project.services;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.entities.Pet;
import project.entities.Photo;
import project.entities.dtos.UserRegisterDTO;
import project.entities.Role;
import project.entities.User;
import project.repositories.RoleRepository;
import project.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final RoleRepository roleRepository;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, ModelMapper mapper, RoleRepository roleRepository, UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.roleRepository = roleRepository;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean register(UserRegisterDTO registerDTO){

        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())){
            return false;
        }

        Optional<User> byEmail = this.userRepository.findByEmail(registerDTO.getEmail());
        Optional<User> byName = this.userRepository.findByUsername(registerDTO.getUsername());

        if (byName.isPresent()){
            return false;
        }

        if (byEmail.isPresent()){
            return false;
        }
        User newUser = mapper.map(registerDTO, User.class);
        newUser.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        Optional<Role> role = this.roleRepository.findById(1L);
        newUser.setRole(role.get());

        this.userRepository.save(newUser);

        return true;
    }

    private void login(User user){
        UserDetails userDetails =
                userDetailsService.loadUserByUsername(user.getUsername());

        Authentication auth =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        userDetails.getPassword(),
                        userDetails.getAuthorities()
                );

        SecurityContextHolder.
                getContext().
                setAuthentication(auth);
    }

    public User currentUser(String username){
        return this.userRepository.findByUsername(username).get();
    }

    public int postedPictures(String username) {
        User user = currentUser(username);
        int sum = 0;
        List<Pet> pets = user.getPets();
        for (Pet pet : pets) {
            sum += pet.getPhotos().size();
        }
        return sum;
    }
}
