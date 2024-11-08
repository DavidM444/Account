package com.manager.revenuemanager.model.services;

import com.manager.revenuemanager.model.entitys.Detail;
import com.manager.revenuemanager.model.entitys.DetailDto;
import com.manager.revenuemanager.model.entitys.MoneyManager;
import com.manager.revenuemanager.model.repositories.DetailRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class DetailService {
    @PersistenceContext
    private EntityManager entityManager;

    private  DetailRepository repository;
    public DetailService(DetailRepository repository) {
        this.repository = repository;
    }

    public List<Detail> obtainData(){
        List<Detail> response = repository.findAll();
        System.out.println("response data: "+response);
        return  response;
    }

    @Cacheable(value = "detail")
    public Page<Detail> pageDetail(int page){
        Pageable pageable = PageRequest.of(page-1, 7, Sort.by("dateHistory").descending());
        return repository.findAll(pageable);
    }

    @CacheEvict(value = "detail", allEntries = true)
    public void saveDetail(Detail detail) {
        repository.save(detail);
    }

    @Cacheable(value = "listdetail")
    public List<Detail> getAllDetailData(){

        System.out.println("obteniendo datos sin cachear");
        return  repository.findAll();}

    public void deleteItemById(UUID id) {
        repository.deleteById(id);
    }

    public Detail convertToDetail(DetailDto detailDto){
        return new Detail(
                detailDto.getDescription(),
                MoneyManager.convertoBigDecimal(detailDto.getAmountHistory()));
    }

    @Transactional
    public BigDecimal getTotalAmountHistory() {
        String jpql = "SELECT SUM(d.amountHistory) FROM Detail d";
        BigDecimal total = entityManager.createQuery(jpql, BigDecimal.class)
                .getSingleResult();
        return total != null ? total : BigDecimal.ZERO; // Retorna cero si no hay resultados
    }
}

