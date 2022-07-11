package project.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.entities.dtos.LoginDTO;
import project.entities.dtos.UserRegisterDTO;
import project.entities.Role;
import project.entities.User;
import project.repositories.RoleRepository;
import project.repositories.UserRepository;
import project.session.LoggedUser;

import javax.validation.Valid;
import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final LoggedUser loggedUser;
    private final RoleRepository roleRepository;

    public AuthService(UserRepository userRepository, ModelMapper mapper, LoggedUser loggedUser, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.loggedUser = loggedUser;
        this.roleRepository = roleRepository;
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

        Optional<Role> role = this.roleRepository.findById(1L);
        newUser.setRole(role.get());

        this.userRepository.save(newUser);

        return true;
    }

    public boolean login(@Valid LoginDTO loginDTO) {

        Optional<User> byUsername = this.userRepository.findByUsername(loginDTO.getUsername());

        if (byUsername.isEmpty()){
            return false;
        }

        if (!byUsername.get().getPassword().equals(loginDTO.getPassword())){
            return false;
        }

        this.loggedUser.login(byUsername.get());
        return true;

    }

    public boolean isLogged(){
        return loggedUser.getId() > 0;
    }

    public void logout(){
        this.loggedUser.logout();
    }

    public long loggedId(){
        return this.loggedUser.getId();
    }
}
