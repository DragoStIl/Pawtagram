package project.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.entities.User;
import project.entities.dtos.UserRegisterDTO;
import project.entities.views.UserView;
import project.services.AuthService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class AuthController {

    private AuthService authService;
    private ModelMapper mapper;

    public AuthController(AuthService authService, ModelMapper mapper) {
        this.authService = authService;
        this.mapper = mapper;
    }

    @ModelAttribute("registerDTO")
    public UserRegisterDTO initRegistrationDTO(){
        return new UserRegisterDTO();
    }


    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegisterDTO registerDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors() || !this.authService.register(registerDTO)){
            redirectAttributes.addFlashAttribute("registerDTO", registerDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerDTO",
                    bindingResult);
            return "redirect:/register";
        }

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/user-profile")
    public String profile(Principal principal, Model model){

        String username = principal.getName();
        User user = authService.currentUser(username);
        UserView userView = mapper.map(user, UserView.class);
        userView.setOwnedAnimals(user.getPets().size());
        //todo implement picture entity, connect it with pet entity
        userView.setPostedPictures(this.authService.postedPictures(username));

        model.addAttribute("user", userView);
        model.addAttribute("pets", userView.getPets());
        System.out.println();
        return "user-profile";
    }


}
