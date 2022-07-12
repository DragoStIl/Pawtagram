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

        model.addAttribute("allPics", this.petService.getAllPetPics());

        return "all";
    }

    @GetMapping("/dogs")
    public String dogPics(Model model){

        model.addAttribute("dogPics", this.petService.getSpecificTypePics("DOG"));

        return "dogs";
    }

    @GetMapping("/cats")
    public String catPics(Model model){

        model.addAttribute("catPics", this.petService.getSpecificTypePics("CAT"));

        return "cats";
    }

    @GetMapping("/exotic")
    public String exoticPics(Model model){

        model.addAttribute("exoticPics", this.petService.getSpecificTypePics("EXOTIC"));

        return "exotic";
    }

}
