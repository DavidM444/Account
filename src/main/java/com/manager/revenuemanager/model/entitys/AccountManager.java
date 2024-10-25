package com.manager.revenuemanager.model.entitys;

import java.math.BigDecimal;

public class AccountManager {

    private Account accountInstance;
    public AccountManager(Account account){
        this.accountInstance = account;
    }

    public boolean realizarOperacion(BigDecimal saldo, String tipo){
        if(saldo.compareTo(accountInstance.getAmount_account())==-1&& tipo.equals("retiro")){
            System.out.println("saldo antes de transaccion: " + accountInstance.getAmount_account());
            accountInstance.sacarSaldo(saldo);
            System.out.println("despues de transaccion: "+accountInstance.getAmount_account());
            return true;
        }
        if (tipo.equals("deposito")){
            System.out.println("depositando "+saldo);
            accountInstance.addSaldo(saldo);
            return true;
        }
        return false;
    }
}
