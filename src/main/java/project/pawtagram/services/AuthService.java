package project.pawtagram.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.pawtagram.entities.User;
import project.pawtagram.entities.dtos.UserRegisterDTO;
import project.pawtagram.repositories.UserRepository;

import java.util.Optional;

@Service
public class AuthService {

    private UserRepository userRepository;
    private ModelMapper mapper;

    public AuthService(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
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
        System.out.println();
        this.userRepository.save(newUser);

        return true;
    }
}
