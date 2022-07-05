package project.pawtagram.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.pawtagram.entities.dtos.AddPetDTO;

import javax.validation.Valid;

@Controller
public class PetController {

    @ModelAttribute("addPetDTO")
    public AddPetDTO initPetRegister(){
        return new AddPetDTO();
    }


    @GetMapping("/pet/add")
    public String addPet(){
        return "pet-add";
    }

    @PostMapping("/pet/add")
    public String addPet(@Valid AddPetDTO addPetDTO ,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("addPetDTO", addPetDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addPetDTO",
                    bindingResult);
            return "redirect:/pet/add";
        }


        return "pet-add";
    }
}
