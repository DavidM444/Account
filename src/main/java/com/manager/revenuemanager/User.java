package com.manager.revenuemanager;

import lombok.Getter;

import java.util.Date;
import java.util.UUID;

@Getter
public class User {
    private UUID uuid;
    private String nombre;
    private String apellido;
    private Date date_created;


    @Override
    public String toString() {
        return "User{" +
                "uuid=" + uuid +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                 +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
