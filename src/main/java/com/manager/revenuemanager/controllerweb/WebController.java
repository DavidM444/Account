package com.manager.revenuemanager.controllerweb;


import com.manager.revenuemanager.model.entitys.*;
import com.manager.revenuemanager.model.services.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
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
        model.addAttribute("detail", new DetailTransaccion());
        model.addAttribute("saldo", Account.getInstance().getAmount_account());
        return "index";
    }

    @GetMapping("/movements")
    public String showHistory(Model model){
        String attribute = "details";
        model.addAttribute(attribute, service.obtainData());
        return "history";
    }

    @PostMapping("/register")
    public String saveUser(Model model,@ModelAttribute DetailTransaccion detail) throws Exception {
        Detail detailToSave = new Detail(detail.getDescription(),detail.getAmountHistory());
        service.saveDetail(detailToSave);
        System.out.println("Model de usuario " +detail.toString());

        AccountManager manager = new AccountManager(Account.getInstance());
        MoneyManager moneyManager = new MoneyManager(String.valueOf(detail.getAmountHistory()));
        //logica para ingresar o sacar saldo de la cuenta
        if(!manager.realizarOperacion(moneyManager.convertoBigDecimal(), detail.getTipo())){
            throw new Exception("Argumentos para el tipo de Transaccion: NO VALIDOS.");
        }
        return "redirect:/";
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
        return "redirect:/movements";
    }
}