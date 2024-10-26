package br.com.lojadebrinquedo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("titulo", "Home - Loja de Brinquedo");
        return "home";
    }
}
