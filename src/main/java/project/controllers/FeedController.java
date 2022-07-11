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

//    @GetMapping("/pet/all")
//    public String dogPics(Model model){
//
//        model.addAttribute("allPics", this.petService.getAllPetPics());
//
//        return "pets";
//    }
//
//    @GetMapping("/pet/all")
//    public String catPics(Model model){
//
//        model.addAttribute("allPics", this.petService.getAllPetPics());
//
//        return "pets";
//    }

    @GetMapping("/exotic")
    public String exoticPics(Model model){

        model.addAttribute("exoticPics", this.petService.getAllExoticPics());

        return "exotic";
    }

}
