package com.manager.revenuemanager.controllerweb;


import com.manager.revenuemanager.exception.SaldoInsuficienteException;
import com.manager.revenuemanager.model.entitys.*;
import com.manager.revenuemanager.model.repositories.AccountRepository;
import com.manager.revenuemanager.model.services.DetailService;
import io.micrometer.common.lang.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@Controller
public class WebController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private DetailService service;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("detail", new DetailDto());
        model.addAttribute("saldo", MoneyManager.convertoString(Account.getInstance().getAmount_account()));
        return "index";
    }

    @GetMapping("/movements")
    public String showHistory(Model model) {
        String attribute = "details";
        model.addAttribute(attribute, service.obtainData());
        return "history";
    }

    @PostMapping("/register")
    public String saveUser(Model model, @ModelAttribute @NonNull DetailDto detailDto) throws Exception {


        Detail detail = service.convertToDetail(detailDto);
        System.out.println("Model de usuario " + detail.toString());
        AccountManager manager = new AccountManager(Account.getInstance());
        MoneyManager moneyManager = new MoneyManager(String.valueOf(detail.getAmountHistory()));
        //logica para ingresar o sacar saldo de la cuenta
        boolean resultOperation = manager.realizarOperacion(moneyManager.convertoBigDecimal(), detailDto.getTipo());
        if (!resultOperation) {
            throw new SaldoInsuficienteException("Argumentos para el tipo de Transacción: NO VALIDOS.");
        }
        accountRepository.setAmountById(Account.getInstance().getAmount_account(), Account.getInstance().getAccount_id());
        Detail detailToSave = new Detail(detail.getDescription(), detail.getAmountHistory());
        service.saveDetail(detailToSave);
        return "redirect:/";
    }

    @PostMapping("/items/delete/{id}")
    public String deleteItem(@PathVariable UUID id) {
        service.deleteItemById(id);
        return "redirect:/movements";
    }
}