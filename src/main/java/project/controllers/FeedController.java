package project.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.entities.Pet;
import project.repositories.PetRepository;
import project.services.PetService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class FeedController {


    private final PetService petService;
    public FeedController(PetService petService) {
        this.petService = petService;
    }


    @GetMapping("/all")
    public String allPics(Model model){

        model.addAttribute("pics", this.petService.getAllPetPics());

        return "feed";
    }

    @GetMapping("/dogs")
    public String dogPics(Model model){

        model.addAttribute("pics", this.petService.getSpecificTypePics("DOG"));

        return "feed";
    }

    @GetMapping("/cats")
    public String catPics(Model model){

        model.addAttribute("pics", this.petService.getSpecificTypePics("CAT"));

        return "feed";
    }

    @GetMapping("/exotic")
    public String exoticPics(Model model){

        model.addAttribute("pics", this.petService.getSpecificTypePics("EXOTIC"));

        return "feed";
    }

    @GetMapping("/pet/{id}")
    public String petAlbum(@PathVariable long id, Model model){
        model.addAttribute("pics", this.petService.getSpecificPetPics(id));
        return "redirect:/feed";
    }

}
