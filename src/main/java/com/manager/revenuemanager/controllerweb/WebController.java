package com.manager.revenuemanager.controllerweb;


import com.manager.revenuemanager.model.entitys.Detail;
import com.manager.revenuemanager.model.services.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
public class WebController {
    @Autowired
    private DetailService service;
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("detail", new Detail());
        return "index";
    }

    @GetMapping("/movements")
    public String login(Model model){
        model.addAttribute("detail", new Detail());
        String attribute = "details";
        model.addAttribute(attribute, service.obtainData());
        return "history";
    }

    @PostMapping("/register")
    public String saveUser(Model model,@ModelAttribute Detail detail){
        Detail detailToSave = new Detail(detail.getDescription(),detail.getAmountHistory());
        System.out.println("Model de usuario " +detail.toString());
        service.saveDetail(detailToSave);
        model.addAttribute("detail", new Detail());
        return "index";
    }

    @GetMapping("/balance")
    public String balance(
            @RequestParam(name = "nombre", required = false, defaultValue = "Nginx") String nombre,
            Model model){
        model.addAttribute("content", "balance :: content");
        model.addAttribute("nombre", nombre);
        return "balance";
    }

    @PostMapping("/items/delete/{id}")
    public String deleteItem(@PathVariable UUID id) {
        service.deleteItemById(id);
        return "redirect:/movements"; // Redirige a la lista de items
    }
}