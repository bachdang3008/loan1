package com.example.loan.controller;

import com.example.loan.entities.InterestRate;
import com.example.loan.service.InterestRateService;
import jakarta.persistence.GeneratedValue;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/intessasasrestrate")
public class InterestRateController {
    public final InterestRateService interestrateService;

    @PostMapping("/post")
    public InterestRate save (@RequestBody InterestRate interestRate) {
       InterestRate db = interestrateService.save(interestRate);
       return db;
    }
    @GetMapping("/get")
    public List<InterestRate> findAllInterestRate() {
       return interestrateService.findAll();
    }

    @Transactional
    @DeleteMapping("/delete/{idinterest}")
    public void delete (@PathVariable("idinterest") int id) {
        interestrateService.DeleteById(id);
    }

}
