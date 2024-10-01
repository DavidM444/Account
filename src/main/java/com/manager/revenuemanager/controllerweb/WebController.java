package com.manager.revenuemanager.controllerweb;


import com.manager.revenuemanager.model.entitys.Detail;
import com.manager.revenuemanager.model.services.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class WebController {
    @Autowired
    private DetailService service;
    @GetMapping("/")
    public String index(Model model){
        return "index";
    }

    @GetMapping("/movements")
    public String login(Model model){
        String attribute = "details";

        model.addAttribute(attribute, service.obtainData());
        return "history";
    }

    @GetMapping("/balance")
    public String balance(
            @RequestParam(name = "nombre", required = false, defaultValue = "Nginx") String nombre,
            Model model){
        model.addAttribute("content", "balance :: content");
        model.addAttribute("nombre", nombre);
        return "balance";
    }



}