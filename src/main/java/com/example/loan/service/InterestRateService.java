package com.example.loan.service;

import com.example.loan.entities.InterestRate;
import com.example.loan.reponsitory.InterestRateReponsitory;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Delete;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InterestRateService {

    public final InterestRateReponsitory reponsitory;

    public InterestRate save (InterestRate interestRate) {
        return reponsitory.save(interestRate);
    }


    public List<InterestRate> findAll() {
        return reponsitory.findAll();
    }

    public void DeleteById(int id) {
        if(reponsitory.findById(id)==null){
            throw new RuntimeException("Id not found");
        }
        reponsitory.deleteById(id);
    }
}



