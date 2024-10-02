package com.manager.revenuemanager.model.repositories;

import com.manager.revenuemanager.model.entitys.Detail;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DetailRepository extends JpaRepository<Detail, UUID> {

}
