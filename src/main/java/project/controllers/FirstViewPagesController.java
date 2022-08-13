package project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import project.entities.Photo;

@Controller
public class FirstViewPagesController {

    
    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model){

        Photo photo = new Photo();
        photo.setUrl("https://media.istockphoto.com/photos/stock-illustration-symbol-question-mark-3d-illustration-isolated-the-picture-id920322646?k=20&m=920322646&s=170667a&w=0&h=VYUvi8UMblmDF6ByYymk8WEhgTFR35mpiuvNj7I_a84=");
        photo.setDescription("Random picture");
        model.addAttribute("photo", photo);
        return "home";
    }

    @GetMapping("/about")
    public String about(){

        return "about";
    }

    @GetMapping("/contacts")
    public String contacts(){

        return "contacts";
    }


}
