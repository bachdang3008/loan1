package com.example.loan.controller;
import com.example.loan.entities.Loan;
import com.example.loan.request.LoanCreateRequest;
import com.example.loan.response.LoanCreateReponse;
import com.example.loan.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Loan")
public class LoanController {

    public final LoanService loanService;

    @PostMapping("/post")
    public LoanCreateReponse createLoan(@RequestBody LoanCreateRequest loanCreateRequest  ){
        return loanService.createLoan(loanCreateRequest);

    }
    @PutMapping("/put/{iduser}")
    public LoanCreateReponse updateUser(@PathVariable("iduser") Long iduser , @RequestBody LoanCreateRequest loanCreateRequest){
        return loanService.updateLoan(iduser, loanCreateRequest) ;
    }

    @GetMapping("/get")
    public List<Loan> getAllLoan(){
        return loanService.getAllLoans();
    }

}
