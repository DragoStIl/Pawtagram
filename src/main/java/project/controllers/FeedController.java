package project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import project.repositories.PetRepository;
import project.services.PetService;

@Controller
public class FeedController {


    private final PetService petService;
    public FeedController(PetRepository petRepository, PetService petService) {
        this.petService = petService;
    }

    @GetMapping("/all")
    public String allPics(Model model){

        model.addAttribute("pics", this.petService.getAllPetPics());

        return "all";
    }

    @GetMapping("/dogs")
    public String dogPics(Model model){

        model.addAttribute("pics", this.petService.getSpecificTypePics("DOG"));

        return "all";
    }

    @GetMapping("/cats")
    public String catPics(Model model){

        model.addAttribute("pics", this.petService.getSpecificTypePics("CAT"));

        return "all";
    }

    @GetMapping("/exotic")
    public String exoticPics(Model model){

        model.addAttribute("pics", this.petService.getSpecificTypePics("EXOTIC"));

        return "all";
    }

}
