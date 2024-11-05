package com.manager.revenuemanager.controllerweb;


import com.manager.revenuemanager.exception.SaldoInsuficienteException;
import com.manager.revenuemanager.model.entitys.*;
import com.manager.revenuemanager.model.repositories.AccountRepository;
import com.manager.revenuemanager.model.services.DetailService;
import io.micrometer.common.lang.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public String showHistory(@RequestParam(defaultValue = "1") int page ,Model model) {
        Page<Detail> pageDetail = service.pageDetail(page);
        return addPaginationModel( pageDetail,page,model);
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
        service.saveDetail(detail);
        return "redirect:/";
    }

    @PostMapping("/items/delete/{id}")
    public String deleteItem(@PathVariable UUID id) {
        service.deleteItemById(id);
        return "redirect:/movements";
    }

    String addPaginationModel(Page<Detail> paginated, int page, Model model){
        List<Detail> listaDetail = paginated.getContent();
        model.addAttribute("details", listaDetail);
        model.addAttribute("pageActual", page);
        model.addAttribute("totalPages", paginated.getTotalPages());
        model.addAttribute("totalElements", paginated.getTotalElements());
        return "history";
    }


}