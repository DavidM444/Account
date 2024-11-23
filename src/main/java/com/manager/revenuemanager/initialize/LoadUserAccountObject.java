package com.manager.revenuemanager.initialize;

import com.manager.revenuemanager.model.entitys.Account;
import com.manager.revenuemanager.model.entitys.User;
import com.manager.revenuemanager.model.repositories.AccountRepository;
import com.manager.revenuemanager.model.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class LoadUserAccountObject {

    private Boolean isDataDbLoaded = false;

    @Autowired
    private UserRepository repository;

    @Autowired
    private AccountRepository accountRepository;

    @PostConstruct
    @Transactional
    public void init(){
        System.out.println("Valor de account " + Account.getInstance().toString() + "  user account "+ User.getInstance().toString());
        System.out.println("llamando metodo void");
        if(accountRepository.count()==0||repository.count()==0){
            saveUserAndAccountData();
        }

        if(Account.getInstance().getAccount_id() == null || User.getInstance().getUid()==null){
            loadEntityDb();
        }
    }
    void saveUserAndAccountData(){
        User userDb = repository.save(User.getInstance());
        User.setUserToInstanceDb(userDb);
        Account accountDbInstance = accountRepository.save(Account.getInstance());
        Account.setToAccountDbInstance(accountDbInstance);
        System.out.println("db instance "+accountDbInstance.toString());
    }

    public void loadEntityDb(){
        User.setUserToInstanceDb(repository.findByClave(User.getInstance().getClave())) ;
        Account.setToAccountDbInstance(accountRepository.getAccountDb(User.getInstance().getUid()));
    }
}
