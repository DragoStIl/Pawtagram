package project.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.entities.dtos.AddPictureDTO;
import project.services.PetService;
import project.services.PictureService;

import javax.validation.Valid;

@Controller
public class PictureController {

    private final PictureService pictureService;
    private long petId;
    private final PetService petService;

    public PictureController(PictureService pictureService, PetService petService) {
        this.pictureService = pictureService;
        this.petService = petService;
    }

    @GetMapping("/add-picture/{id}")
    public String pictureAdd(@PathVariable long id, Model model) {
        petId = this.petService.findById(id).getId();

        System.out.println();

        return "redirect:/add-pic";
    }

    @PostMapping("/add-picture")
    public String pictureAdd(@Valid AddPictureDTO addPictureDTO ,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             @AuthenticationPrincipal UserDetails userDetails){


        if (bindingResult.hasErrors() || !this.pictureService.add(addPictureDTO)){
            redirectAttributes.addFlashAttribute("addPictureDTO", addPictureDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addPictureDTO",
                    bindingResult);
            return "redirect:/add-pic";
        }

        return "redirect:/add-pic";
    }
}
