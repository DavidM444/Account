package com.manager.revenuemanager.model.entitys;

import com.manager.revenuemanager.model.entitys.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Entity
@Table(name = "detail")
public class Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID deId;

    @ManyToOne
    @JoinColumn(name = "detail_account_id")
    private Account detailAccountId;

    private LocalDate dateHistory;
    private Double amountHistory;
    private String description;


    public Detail(){
    }


}
