package project.pawtagram.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }
}