package com.example.loan.controller;
import com.example.loan.entities.Loan;
import com.example.loan.request.LoanCreateRequest;
import com.example.loan.response.LoanCreateResponse;
import com.example.loan.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/loan")
public class LoanController {

    public final LoanService loanService;

    @PostMapping("")
    public LoanCreateResponse createLoan(@RequestBody LoanCreateRequest loanCreateRequest, @AuthenticationPrincipal Jwt jwt){
        Long userId = jwt.getClaim("userId");
        return loanService.createLoan(loanCreateRequest, userId);

    }
    @PutMapping("/{loanId}")
    public LoanCreateResponse updateUser(@PathVariable("loanId") Long loanId, @RequestBody LoanCreateRequest loanCreateRequest, @AuthenticationPrincipal Jwt jwt){
        Long userId = jwt.getClaim("userId");
        return loanService.updateLoan(userId, loanCreateRequest) ;
    }

    @GetMapping("")
    public List<Loan> getAllLoan(){
        return loanService.getAllLoans();
    }

}
