package com.manager.revenuemanager.model.services;

import com.manager.revenuemanager.model.entitys.User;
import com.manager.revenuemanager.model.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository){
        this.repository = repository;
    }

    private User findUserById(UUID uuid){
        return repository.findById(uuid).orElseThrow(RuntimeException::new);
    }

    private void saveUser(User user){
        repository.save(user);
    }
}
