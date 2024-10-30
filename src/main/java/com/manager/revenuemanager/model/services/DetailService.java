package com.manager.revenuemanager.model.services;

import com.manager.revenuemanager.model.entitys.Detail;
import com.manager.revenuemanager.model.repositories.DetailRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DetailService {

    private  DetailRepository repository;
    public DetailService(DetailRepository repository) {
        this.repository = repository;
    }

    public List<Detail> obtainData(){
        List<Detail> response = repository.findAll();
        System.out.println("response data: "+response);
        return  response;
    }

    public Page<Detail> page(int page, int size ){
        Pageable pageable = PageRequest.of(page,size);
        return repository.findAll(pageable);
    }

    public void saveDetail(Detail detail) {
        repository.save(detail);
    }

    public void deleteItemById(UUID id) {
        repository.deleteById(id);
    }
}
