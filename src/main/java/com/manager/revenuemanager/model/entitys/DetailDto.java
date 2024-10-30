package com.manager.revenuemanager.model.entitys;

public class DetailDto {
    private String tipo;
    private String amount;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount){
        this.amount = amount;
    }

    public DetailDto(){}

    public DetailDto(String tipo, String amount){
        super();
        this.tipo = tipo;
        this.amount = amount;
    }
}
