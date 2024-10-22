package com.manager.revenuemanager.model.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Getter
@Entity
@ToString
@AllArgsConstructor
@Table(name = "users")
public class User {

    public static void setUserInstance(User userInstance) {
        User.userInstance = userInstance;
    }

    private static User userInstance;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uid;
    private String nombre;
    private String apellido;

    public User(String nombre, String apellido, String clave, LocalDate date_created) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.clave = clave;
        this.date_created = date_created;
    }

    private String clave;
    private LocalDate date_created;

    public User(){}
    public static User getInstance(){
        if(userInstance == null){
            return new User("David","Muñoz","fakepassword2000", LocalDate.now().minusYears(2));
        }
        return userInstance;
    }


    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
