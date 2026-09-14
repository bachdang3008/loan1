package com.example.loan.controller;

import com.example.loan.entities.InterestRate;
import com.example.loan.service.InterestRateService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/interest-rate")
public class InterestRateController {
    public final InterestRateService interestrateService;

    @PostMapping("")
    public InterestRate save (@RequestBody InterestRate interestRate) {
       InterestRate db = interestrateService.save(interestRate);
       return db;
    }
    @GetMapping("")
    public List<InterestRate> findAllInterestRate() {
       return interestrateService.findAll();
    }

    @Transactional
    @DeleteMapping("{interestId}")
    public void delete (@PathVariable("intesrestId")  int id) {
        interestrateService.deleteById(id);
    }

}
