package com.manager.revenuemanager.initialize;

import com.manager.revenuemanager.model.entitys.Account;
import com.manager.revenuemanager.model.entitys.User;
import com.manager.revenuemanager.model.repositories.AccountRepository;
import com.manager.revenuemanager.model.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoadUserAccountObject {

    private static Account accountDbInstance;

    @Autowired
    private UserRepository repository;

    @Autowired
    private AccountRepository accountRepository;

    @PostConstruct
    public void init(){
        if(repository.count()==0 && accountRepository.count()==0){
            User user = User.getInstance();
            User user1 = repository.save(user);

            // setear instancia de usuario para trabajar con singleton
            //User.setUserInstance(user1);
            Account account = Account.getInstance();
            account.setUserId(user1);
            accountDbInstance = accountRepository.save(account);
            System.out.println("db instance "+accountDbInstance.toString());
        }
    }

    public static Account getAccountDbInstance(){
        if(accountDbInstance.getUserId().getUid() ==null){
            throw new RuntimeException("User not Found with ID: " + User.getInstance().getUid());
        }
        return accountDbInstance;
    }
}
