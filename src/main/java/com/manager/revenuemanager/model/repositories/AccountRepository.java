package com.manager.revenuemanager.model.repositories;

import com.manager.revenuemanager.initialize.LoadUserAccountObject;
import com.manager.revenuemanager.model.entitys.Account;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

public interface AccountRepository extends CrudRepository<Account, UUID> {

    @Query("SELECT account from Account account where account.account_id=:id")
    @Transactional(readOnly = true)
    Account findAccount(@Param("id") UUID id);


//    @Query("SELECT COUNT(a) FROM Account a")
//    @Transactional
//    long count();

    // crear metodo para que actualice el valor del amount de la cuenta luego de cada agregacion de un detail.
    @Modifying //indica que es una cosulta como update o delete
    @Query("UPDATE Account a SET a.amount_account = :amount WHERE a.account_id = :id")
    @Transactional
    void setAmountById(@Param("amount")BigDecimal amount, @Param("id") UUID id);

    @Query("SELECT a FROM Account a WHERE a.userId.uid = :userid")
    @Transactional(readOnly = true)
    Account getAccountDb(@Param("userid") UUID userid);

}
