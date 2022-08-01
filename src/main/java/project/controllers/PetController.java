package project.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.entities.Pet;
import project.entities.dtos.AddPetDTO;
import project.services.PetService;

import javax.validation.Valid;

@Controller
public class PetController {

    private PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @ModelAttribute("addPetDTO")
    public AddPetDTO initPetRegister(){

        return new AddPetDTO();
    }


    @GetMapping("/pet")
    public String addPet(Model model){

        if (!model.containsAttribute("addPetDto")){
            model.addAttribute("addPetDto", new AddPetDTO());
        }
        model.addAttribute("types", petService.getTypes());
        return "pet-add";
    }

    @PostMapping("/pet")
    public String addPet(@Valid AddPetDTO addPetDTO ,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes,
                         @AuthenticationPrincipal UserDetails userDetails){

        if (bindingResult.hasErrors() ){
            redirectAttributes.addFlashAttribute("addPetDTO", addPetDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addPetDTO",
                    bindingResult);
            return "redirect:/pet";
        }

        if (!this.petService.addPet(addPetDTO, userDetails)){
            redirectAttributes.addFlashAttribute("addPetDTO", addPetDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addPetDTO",
                    bindingResult);
            return "redirect:/pet";
        }

        return "redirect:/home";
    }


//    @GetMapping("/pet-profile")
//    public String petProfile(@PathVariable long id, Model model){
//        Pet byId = this.petService.findById(id);
//
//
//        model.addAttribute("pet", byId);
//        return "redirect:/pet-profile";
//    }

    @GetMapping("/pet-profile")
    public String petProfile(Model model){
        Pet byId = this.petService.findById(1);


        model.addAttribute("pet", byId);
        return "redirect:/pet-profile";
    }

}
