package project.services;


import org.springframework.stereotype.Service;
import project.entities.Picture;
import project.entities.dtos.AddPictureDTO;
import project.repositories.PictureRepository;

import java.util.Optional;

@Service
public class PictureService {

    public PictureRepository pictureRepository;

    public boolean add(AddPictureDTO addPictureDTO) {

        Optional<Picture> byUrl = this.pictureRepository.findByUrl(addPictureDTO.getUrl());
        if (byUrl.isEmpty()){
            return false;
        }
        return true;
    }
}
