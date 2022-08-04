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
import project.entities.dtos.AddPetDTO;
import project.entities.dtos.AddPhotoDTO;
import project.entities.views.PetView;
import project.services.PetService;
import project.services.PhotoService;

import javax.validation.Valid;

@Controller
public class PetController {

    private final PetService petService;

    private long petId = 0;
    private final PhotoService photoService;

    public PetController(PetService petService, PhotoService photoService) {
        this.petService = petService;
        this.photoService = photoService;
    }

    @ModelAttribute("addPetDTO")
    public AddPetDTO initPetRegister(){

        return new AddPetDTO();
    }

    @ModelAttribute("addPhotoDTO")
    public AddPhotoDTO initAddPicture(){

        return new AddPhotoDTO();
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




    @GetMapping("/pet-profiles/{id}")
    public String petProfile(@PathVariable String id, Model model){

        petId = Long.parseLong(id);
        return "redirect:/pet-profiles";
    }

    @GetMapping("/pet-profiles")
    public String basicProfile(Model model){

        if(petId == 0){
            return "redirect:/home";
        }
        PetView petView = this.petService.getPetView(petId);
        if(!model.containsAttribute("pet")){
            model.addAttribute("pet", petView);
        }else {
            model.addAttribute("pet", petView);
        }
        return "pet-profiles";
    }

    @GetMapping("/add-pic")
    public String addPic(Model model){

        if (!model.containsAttribute("addPhotoDto")){
            model.addAttribute("addPhotoDto", new AddPhotoDTO());
        }

        return "add-pic";
    }

    @PostMapping("/add-pic")
    public String addPic(@Valid AddPhotoDTO addPhotoDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes,
                         @AuthenticationPrincipal UserDetails userDetails){


        if (bindingResult.hasErrors() || !this.photoService.add(petId, addPhotoDTO)){
            redirectAttributes.addFlashAttribute("addPhotoDTO", addPhotoDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addPhotoDTO",
                    bindingResult);
            return "redirect:/add-pic";
        }

        return "redirect:/pet-profiles";
    }


}
