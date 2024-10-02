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

    @Autowired
    private UserRepository repository;

    @Autowired
    private AccountRepository accountRepository;

    @PostConstruct
    public void init(){
        if(repository.count()==0 && accountRepository.count()==0){
            User user = User.getInstance();
            User user1 = repository.save(user);

            Account account = Account.getInstance();
            account.setUserId(user1);
            accountRepository.save(account);
        }
    }
}
