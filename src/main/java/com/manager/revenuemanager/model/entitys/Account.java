package com.manager.revenuemanager.model.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID account_id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "uid")
    private User userId;


    @Setter
    @Column(precision = 12, scale = 2)
    private BigDecimal amount_account;


    private static Account account;

    private static void setAccount(Account account) {
        Account.account = account;
    }

    public Account(User user, BigDecimal amount) {
        this.userId = user;
        this.amount_account = amount;
    }

    public Account() {
    }

    // Usar cuando tenga una relación bidireccional entre dos entidades, ya que genera ciclos de serializacion, generadno
    //bucles entre las entidades, donde una referencia la otra y viceversa. Usar JsonIgnore en la relacion inversa para evitar la serializacion de ese campo
    @JsonIgnore
    @OneToMany(mappedBy = "detailAccountId", fetch = FetchType.LAZY) //no usar y mejor retonar los datos de forma manual
    List<Detail> listDetails = new LinkedList<>();

    public static Account getInstance() {
        if (account == null) {
            account = new Account(User.getInstance(), new BigDecimal("0"));
        }
        return account;
    }

    public static void setToAccountDbInstance(Account accountDb) {
        if (account.getAccount_id() == null) {
            setAccount(accountDb);
        }
    }

    void addSaldo(BigDecimal saldo) {
        account.setAmount_account(account.getAmount_account().add(saldo));
        System.out.println("saldo actual en addSaldo: " + saldo + "--- " + "   tot " + (account.getAmount_account()));
    }

    void sacarSaldo(BigDecimal saldo) {
        BigDecimal saldoActual = account.getAmount_account();
        account.setAmount_account(account.getAmount_account().subtract(saldo));

    }


    @Override
    public String toString() {
        return "Account{" +
                "account_id=" + account_id +
                ", amount_account=" + amount_account +
                '}';
    }

}
