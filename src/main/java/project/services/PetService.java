package project.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.entities.Pet;
import project.entities.TypeEntity;
import project.entities.dtos.AddPetDTO;
import project.repositories.PetRepository;
import project.repositories.TypeRepository;
import project.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetService {
    private TypeRepository typeRepository;
    private PetRepository petRepository;
    private ModelMapper mapper;
    private AuthService authService;
    private UserRepository userRepository;

    public PetService(TypeRepository typeRepository, PetRepository petRepository, ModelMapper mapper, AuthService authService, UserRepository userRepository) {
        this.typeRepository = typeRepository;
        this.petRepository = petRepository;
        this.mapper = mapper;
        this.authService = authService;
        this.userRepository = userRepository;
    }

    public List<TypeEntity> getTypes() {


        List<TypeEntity> types = new ArrayList<>(typeRepository.findAll());

        System.out.println();
        return types;
    }

    public boolean addPet(AddPetDTO addPetDTO){
        Optional<Pet> byImageUrl = this.petRepository.findByImageUrl(addPetDTO.getImageUrl());
        if(byImageUrl.isPresent()){
            return false;
        }
        Pet pet = mapper.map(addPetDTO, Pet.class);
        pet.setType(this.typeRepository.findById(addPetDTO.getType()).get());
//        pet.setOwner(this.userRepository.findById(this.authService.loggedId()).get());
        this.petRepository.save(pet);
        return true;
    }

    public List<Pet> getAllPetPics() {

        return this.petRepository.findAll();
    }

    public List<Pet> getSpecificTypePics(String type) {
        List<Pet> pets = this.getAllPetPics()
                .stream()
                .filter(p -> p.getType()
                        .getType().name()
                        .equals(type))
                .toList();

        System.out.println();

        return pets;
    }

}
