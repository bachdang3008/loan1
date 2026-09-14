package com.example.loan.service;

import com.example.loan.entities.InterestRate;
import com.example.loan.entities.Loan;
import com.example.loan.entities.User;
import com.example.loan.reponsitory.InterestRateReponsitory;
import com.example.loan.reponsitory.LoanReponsitory;
import com.example.loan.reponsitory.UserReponsitory;
import com.example.loan.request.LoanCreateRequest;
import com.example.loan.response.LoanCreateResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanService {

    public final LoanReponsitory loanReponsitory;
    public final InterestRateReponsitory interestRateReponsitory;
    public final UserReponsitory userReponsitory;

    @Transactional
    public LoanCreateResponse createLoan(LoanCreateRequest loanCreateRequest, Long userId) {
        User user = userReponsitory.findUsersById(userId)
                .orElseThrow(()-> new RuntimeException("User not found"));
        InterestRate interestRateEntity = interestRateReponsitory.findByMonth(loanCreateRequest.getMonth())
                .orElseThrow(()-> new RuntimeException(("\"Term/interest rate not found for the number of months: " + loanCreateRequest.getMonth())));

        String interestRateStr = interestRateEntity.getInterestRate();
        double interestRate = Double.parseDouble(interestRateStr.replace("%", "").trim());

        double loanAmount = Double.parseDouble(loanCreateRequest.getLoanAmount());
        int month = Integer.parseInt(loanCreateRequest.getMonth());


        double  amountPayable = loanAmount + (month * loanAmount * (interestRate/100)) ;
        double MonthlyPaymentAmount = (loanAmount/month) + (loanAmount * (interestRate/100));

        LocalDate createDate = LocalDate.now();
        LocalDate paymentDate = createDate.plusDays(30);

        Loan loan = Loan.builder()
                .loanAmount(loanAmount)
                .month(loanCreateRequest.getMonth())
                .interestRate(interestRate)
                .cccd(loanCreateRequest.getCccd())
                .amountPayable(amountPayable)
                .monthlyPaymentAmount(MonthlyPaymentAmount)
                .createDate(createDate)
                .paymentDate(paymentDate)
                .status("Pending")
                .user(user)
                .build();



        loanReponsitory.save(loan);

        return LoanCreateResponse.builder()
                .loanAmount(String.valueOf(loan.getLoanAmount()))
                .month(loan.getMonth())
                .cccd(loan.getCccd())
                .interestRate(loan.getInterestRate())
                .amountPayable(loan.getAmountPayable())
                .monthlyPaymentAmount(loan.getMonthlyPaymentAmount())
                .createDate(loan.getCreateDate())
                .paymentDate(loan.getPaymentDate())
                .status(loan.getStatus())
                .user(user)
                .build();
    }

    @Transactional
    public LoanCreateResponse updateLoan(Long loanId, LoanCreateRequest request) {

        Loan loan = loanReponsitory.findById(loanId)
                .orElseThrow(()-> new RuntimeException("Loan not found"));
        double loanAmount = Double.parseDouble(request.getLoanAmount());
        loan.setLoanAmount(loanAmount);
        loan.setMonth(request.getMonth());
        loan.setCccd(request.getCccd());


        loanReponsitory.save(loan);

        return LoanCreateResponse.builder()
                .loanAmount(String.valueOf(loan.getLoanAmount()))
                .month(loan.getMonth())
                .cccd(loan.getCccd())
                .interestRate(loan.getInterestRate())
                .amountPayable(loan.getAmountPayable())
                .monthlyPaymentAmount(loan.getMonthlyPaymentAmount())
                .createDate(loan.getCreateDate())
                .paymentDate(loan.getPaymentDate())
                .status(loan.getStatus())
                .user(loan.getUser())
                .build();
    }


    public List<Loan> getAllLoans() {
        return loanReponsitory.findAll();
    }



}
