package com.manager.revenuemanager.model.entitys;

public class DetailDto {

    private String description;
    private String tipo;
    private String amountHistory;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAmountHistory() {
        return amountHistory;
    }

    public void setAmountHistory(String amountHistory){
        this.amountHistory = amountHistory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DetailDto(){}

    public DetailDto(String description,String tipo, String amount){
        super();
        this.tipo = tipo;
        this.amountHistory = amount;
        this.description = description;
    }
}
