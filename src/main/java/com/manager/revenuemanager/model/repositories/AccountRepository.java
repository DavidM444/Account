package com.manager.revenuemanager.model.repositories;

import com.manager.revenuemanager.model.entitys.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface AccountRepository extends CrudRepository<Account, UUID> {

    @Query("SELECT account from Account account where account.account_id=:id")
    @Transactional(readOnly = true)
    Account findAccount(@Param("id") UUID id);


//    @Query("SELECT COUNT(a) FROM Account a")
//    @Transactional
//    long count();
}
