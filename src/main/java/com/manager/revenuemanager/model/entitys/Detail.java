package com.manager.revenuemanager.model.entitys;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Entity
//@ToString  -> genera desborde de memoria debido a recursion mutua del metodo toString
@Table(name = "detail")
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID deId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "detail_account_id")
    private Account detailAccountId;
    private LocalDate dateHistory;
    private BigDecimal amountHistory;
    private String description;

    public Detail(String description, BigDecimal amountHistory){
        this.description = description;
        this.amountHistory = amountHistory;
        this.dateHistory = LocalDate.now().minusYears(3);
        //Use DB instance to persist Detail Object
        this.detailAccountId = Account.getInstance();
    }

    public Detail(){
    }

    @Override
    public String toString() {
        return "Detail{" +
                "deId=" + deId +
                ", detailAccountId=" + detailAccountId +
                ", dateHistory=" + dateHistory +
                ", amountHistory=" + amountHistory +
                ", description='" + description + '\'' +
                '}';
    }
}
