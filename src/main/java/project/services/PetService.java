package project.services;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import project.entities.Pet;
import project.entities.Photo;
import project.entities.TypeEntity;
import project.entities.dtos.AddPetDTO;
import project.entities.views.PetView;
import project.repositories.PetRepository;
import project.repositories.TypeRepository;
import project.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        return types;
    }

    public boolean addPet(AddPetDTO addPetDTO, UserDetails userDetails){
        Optional<Pet> byImageUrl = this.petRepository.findByImageUrl(addPetDTO.getImageUrl());
        if(byImageUrl.isPresent()){
            return false;
        }
        Pet pet = mapper.map(addPetDTO, Pet.class);
        pet.setType(this.typeRepository.findById(addPetDTO.getType()).get());
        pet.setOwner(this.userRepository.findByUsername(userDetails.getUsername()).orElseThrow());
        this.petRepository.save(pet);
        return true;
    }

    public List<Photo> getAllPetPics() {
        return getPictures(this.petRepository.findAll());
    }


    public List<Photo> getSpecificPetPics(long id) {
        return this.petRepository.findById(id).get().getPhotos();
    }

    public List<Photo> getSpecificTypePics(String type) {
        List<Pet> pets = this.petRepository.findAll()
                .stream()
                .filter(p -> p.getType()
                        .getType().name()
                        .equals(type))
                .toList();

        return getPictures(pets);
    }

    private List<Photo> getPictures(List<Pet> pets) {
        List<Photo> photos = new ArrayList<>();
        for (Pet pet : pets) {
            photos.addAll(pet.getPhotos());
        }
        return photos;
    }

    public Pet findById(long id) {
        return this.petRepository.findById(id).get();
    }

    public PetView getPetView(long petId) {
        Pet pet = findById(petId);
        return new PetView(pet.getId(), pet.getName(), pet.getAge(), pet.getImageUrl(), pet.getPhotos().size());
    }
}
