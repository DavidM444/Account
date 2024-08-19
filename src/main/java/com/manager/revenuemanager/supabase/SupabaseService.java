package com.manager.revenuemanager.supabase;

import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class SupabaseService {

    private static String SUPABASE_URL;
    private String SUPABASE_KEY;
    private RestTemplate restTemplate;

    public SupabaseService(){
        this.restTemplate = new RestTemplate();
        loadSupabaseCredentials();


    }

    public void loadSupabaseCredentials() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/supabase.properties"));
        }catch (IOException ex){
            throw new RuntimeException(ex.getCause());
        }
        SUPABASE_URL = properties.getProperty("SB-URL");
        SUPABASE_KEY= properties.getProperty("SB-KEY");
    }



    public String getData(){
        return this.SUPABASE_KEY;
    }

}
