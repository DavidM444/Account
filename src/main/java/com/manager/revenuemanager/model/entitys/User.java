package com.manager.revenuemanager.model.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Getter
@Entity
@AllArgsConstructor
@Table(name = "users")
public class User {

    private static User userInstance;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uid;
    private String nombre;
    private String apellido;
    private String clave;
    private LocalDate date_created;

    public User(){}
    public static User getInstance(){
        if(userInstance == null){
            return new User(UUID.randomUUID(),"David","Muñoz","fakepassword2000", LocalDate.now());
        }
        return userInstance;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
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
