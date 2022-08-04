package project.services;

import org.springframework.stereotype.Service;
import project.entities.Photo;
import project.entities.dtos.AddPhotoDTO;
import project.repositories.PhotoRepository;

@Service
public class PhotoService {

    private final PhotoRepository photoRepository;
    private PetService PetService;

    public PhotoService(PhotoRepository photoRepository, project.services.PetService petService) {
        this.photoRepository = photoRepository;
        PetService = petService;
    }


    public boolean add(long petId, AddPhotoDTO addPhotoDTO) {
        this.photoRepository
                .save(new Photo(addPhotoDTO.getUrl(), addPhotoDTO.getDescription(), this.PetService.findById(petId)));

        return true;
    }
}
