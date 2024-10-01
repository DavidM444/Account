package com.manager.revenuemanager.model.entitys;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Getter
@ToString
@Entity
@Table(name = "account")
public class Account {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private final UUID account_id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "uid")
    private User userId;

    private Double amount_account;


    private static Account account;
    public Account(User user, Double amount){
        this.account_id = UUID.randomUUID();
        this.userId = user;
        this.amount_account = amount;

    }

    @OneToMany(mappedBy = "detailAccountId") //no usar y mejor retonar los datos de forma manual
    List<Detail> listDetails = new LinkedList<>();

    public static Account getInstance(){
        if(account ==null){
            return new Account(User.getInstance(), 100.000);
        }
        return account;
    }
}
