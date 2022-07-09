package project.services;

import org.springframework.stereotype.Service;
import project.entities.TypeEntity;
import project.repositories.TypeRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PetService {
    private TypeRepository typeRepository;

    public PetService(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    public List<TypeEntity> getTypes() {


        List<TypeEntity> types = new ArrayList<>(typeRepository.findAll());

        System.out.println();
        return types;
    }
}
