package com.manager.revenuemanager.model.entitys;

import java.time.LocalDate;
import java.util.UUID;

public class DetailTransaccion extends Detail{
    private String tipo;

    public DetailTransaccion(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public DetailTransaccion(UUID deId, Account detailAccountId, LocalDate dateHistory, String amountHistory, String description, String tipo) {
        super(deId, detailAccountId, dateHistory, MoneyManager.convertoBigDecimal(amountHistory), description);
        this.tipo = tipo;
    }


    @Override
    public String toString() {
        return "DetailTransaccion{" +
                "tipo='" + tipo + '\'' +
                '}';
    }

    public DetailTransaccion(){}


}
