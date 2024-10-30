package com.manager.revenuemanager.model.repositories;

import com.manager.revenuemanager.model.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.UUID;

public interface UserRepository extends JpaRepository< User, UUID> {
    User findByClave(String password);
}
