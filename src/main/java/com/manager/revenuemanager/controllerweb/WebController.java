package com.manager.revenuemanager.controllerweb;

import com.manager.revenuemanager.Account;
import com.manager.revenuemanager.User;
import com.manager.revenuemanager.balance.Balance;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("content", "home :: content" );
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("contentFragment", "login :: content");
        return "login";
    }

    @GetMapping("/balance")
    public String balance(
            @RequestParam(name = "nombre", required = false, defaultValue = "Nginx") String nombre,
            Model model){
        model.addAttribute("content", "balance :: content");
        model.addAttribute("nombre", nombre);
        return "balance";
    }
    @GetMapping("/home")
    public String home(
            Model model){
        User user = new User();
        Account account = new Account(user);
        Balance balance = new Balance(account);

        model.addAttribute("actualBalance", String.valueOf(balance.obtainBalance()));
        return "home";
    }

//    @GetMapping("/prueba")
//    public String showPrueba(Model model){
//        model.addAttribute("content", "frag :: navbar");
//        return "prueba";
//
//    }
//    @GetMapping("/prueba/p1")
//    public  String showp1(Model model){
//        System.out.println("ruta p1");
//        model.addAttribute("navbar", "frag :: navbar");
//        return "prueba";
//    }
}