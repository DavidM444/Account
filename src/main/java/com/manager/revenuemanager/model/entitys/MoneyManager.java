package com.manager.revenuemanager.model.entitys;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class MoneyManager {
    static NumberFormat formatSaldo = NumberFormat.getCurrencyInstance( new Locale("es","CO"));
    //Currency representa la moneda de un pais, con esta clase puedo obtener los decimales, simbolo, codigo(COP) y la instancia de esa moneda.
    //Ademas acepta objeto Locale, para obtener una moneda de acurdo a una localizacion.
    static Currency currency = Currency.getInstance("COP");
    private static String saldo;
    public MoneyManager(String monto){
        saldo = monto;
    }

    public BigDecimal convertoBigDecimal(){
        String valorSinMiles = saldo.replace(".","");
        return new BigDecimal(valorSinMiles);
    }
    public static BigDecimal convertoBigDecimal(String saldo){
        System.out.println("recibiendo valor a cambiar "+saldo);
        String valorSinMiles = saldo.replace(".","");

        System.out.println("valor cambiado: "+valorSinMiles +" "+saldo);
        return new BigDecimal(valorSinMiles);
    }

    public static String convertoString(BigDecimal bigDecimal){
        formatSaldo.setCurrency(currency);
     //definimos el formateador con moneda COP
        String formmatedBalance = formatSaldo.format(bigDecimal);
        return formmatedBalance.replace("$","");
    }

}
