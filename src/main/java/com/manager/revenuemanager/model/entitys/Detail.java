package com.manager.revenuemanager.model.entitys;

import com.manager.revenuemanager.initialize.LoadUserAccountObject;
import com.manager.revenuemanager.model.entitys.Account;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
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

    public Detail(String description, Double amountHistory){
        this.description = description;
        this.amountHistory = amountHistory;
        this.dateHistory = LocalDate.now().minusYears(3);
        //Use DB instance to persist Detail Object
        this.detailAccountId = LoadUserAccountObject.getAccountDbInstance();
    }

    public Detail(){
    }
}
