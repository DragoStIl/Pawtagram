package project.pawtagram.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.pawtagram.entities.dtos.LoginDTO;
import project.pawtagram.entities.dtos.UserRegisterDTO;
import project.pawtagram.services.AuthService;
import javax.validation.Valid;

@Controller
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @ModelAttribute("registerDTO")
    public UserRegisterDTO initRegistrationDTO(){
        return new UserRegisterDTO();
    }

    @ModelAttribute("loginDTO")
    public LoginDTO initLoginDTO(){
        return new LoginDTO();
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

    @PostMapping("/login")
    public String login(@Valid LoginDTO loginDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){

            redirectAttributes.addFlashAttribute("loginDTO", loginDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginDTO",
                    bindingResult);

            return "redirect:/login";
        }

        if (!this.authService.login(loginDTO)){
            redirectAttributes.addFlashAttribute("loginDTO", loginDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginDTO",
                    bindingResult);

            return "redirect:/login";
        }



        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(){
        this.authService.logout();
        return "redirect:/";
    }
}
