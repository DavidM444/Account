package com.manager.revenuemanager.supabase;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class SupabaseService {

    private static String SUPABASE_URL;
    private static String SUPABASE_KEY;
    private RestTemplate restTemplate;

    public SupabaseService(){
        this.restTemplate = new RestTemplate();
    }
    static {
        loadSupabaseCredentials();
    }

    public String doPeticion(){
        String url = SUPABASE_URL+"/rest/v1/Carros?select=*";
        HttpHeaders headers = new HttpHeaders();
        System.out.println("url "+SUPABASE_URL);
        headers.set("apikey", SUPABASE_KEY);
        headers.set("Authorization", "Bearer " + SUPABASE_KEY);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET,entity, String.class);
        return response.getBody();
    }

    public static void loadSupabaseCredentials() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/supabase.properties"));
        }catch (IOException ex){
            throw new RuntimeException(ex.getMessage());
        }
        SUPABASE_URL = properties.getProperty("SB_URL");
        SUPABASE_KEY= properties.getProperty("SB_KEY");
    }



    public String getData(){
        return SUPABASE_KEY;
    }

}

@Component
class Llamada{
    public static void main(String[] args) {

        SupabaseService service = new SupabaseService();
        System.out.println("Data ---- "+service.getData());
        System.out.println("ddd "+service.doPeticion());
    }

}