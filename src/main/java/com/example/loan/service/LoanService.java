package com.example.loan.service;

import com.example.loan.entities.InterestRate;
import com.example.loan.entities.Loan;
import com.example.loan.entities.User;
import com.example.loan.reponsitory.InterestRateReponsitory;
import com.example.loan.reponsitory.LoanReponsitory;
import com.example.loan.reponsitory.UserReponsitory;
import com.example.loan.request.LoanCreateRequest;
import com.example.loan.response.LoanCreateReponse;
import com.example.loan.response.UserCreateResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoanService {

    public final LoanReponsitory loanReponsitory;
    public final InterestRateReponsitory interestRateReponsitory;
    public final UserReponsitory userReponsitory;

    @Transactional
    public LoanCreateReponse createLoan(LoanCreateRequest loanCreateRequest) {
        User user = userReponsitory.findUsersById(loanCreateRequest.getId())
                .orElseThrow(()-> new RuntimeException("User not found"));
        InterestRate interestRateEntity = interestRateReponsitory.findByMonth(loanCreateRequest.getMonth())
                .orElseThrow(()-> new RuntimeException(("\"Term/interest rate not found for the number of months: " + loanCreateRequest.getMonth())));

        String interestRateStr = interestRateEntity.getInterestRate();
        double interestRate = Double.parseDouble(interestRateStr.replace("%", "").trim());

        double loanAmount = Double.parseDouble(loanCreateRequest.getLoanamount());
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
                .MonthlyPaymentAmount(MonthlyPaymentAmount)
                .createDate(createDate)
                .paymentDate(paymentDate)
                .status("Pending")
                .build();

        UserCreateResponse userCreateResponse = UserCreateResponse.builder()
                .email(user.getEmail())
                .name(user.getName())
                .phone(user.getPhone())
                .age(user.getAge())
                .address(user.getAddress())
                .build();


        loanReponsitory.save(loan);

        return LoanCreateReponse.builder()
                .loanAmount(String.valueOf(loan.getLoanAmount()))
                .month(loan.getMonth())
                .cccd(loan.getCccd())
                .interestRate(loan.getInterestRate())
                .amountPayable(loan.getAmountPayable())
                .MonthlyPaymentAmount(loan.getMonthlyPaymentAmount())
                .createDate(loan.getCreateDate())
                .paymentDate(loan.getPaymentDate())
                .status(loan.getStatus())
                .UserInfor(userCreateResponse)
                .build();
    }

    @Transactional
    public LoanCreateReponse updateLoan(Long loanId, LoanCreateRequest request) {

        Loan loan = loanReponsitory.findById(loanId)
                .orElseThrow(()-> new RuntimeException("Loan not found"));
        double loanAmount = Double.parseDouble(request.getLoanamount());
        loan.setLoanAmount(loanAmount);
        loan.setMonth(request.getMonth());
        loan.setCccd(request.getCccd());

        User user = userReponsitory.findUsersById(request.getId())
                .orElseThrow(()-> new RuntimeException("User not found"));

        UserCreateResponse userCreateResponse = UserCreateResponse.builder()
                .email(user.getEmail())
                .name(user.getName())
                .phone(user.getPhone())
                .age(user.getAge())
                .address(user.getAddress())
                .build();

        loanReponsitory.save(loan);

        return LoanCreateReponse.builder()
                .loanAmount(String.valueOf(loan.getLoanAmount()))
                .month(loan.getMonth())
                .cccd(loan.getCccd())
                .interestRate(loan.getInterestRate())
                .amountPayable(loan.getAmountPayable())
                .MonthlyPaymentAmount(loan.getMonthlyPaymentAmount())
                .createDate(loan.getCreateDate())
                .paymentDate(loan.getPaymentDate())
                .status(loan.getStatus())
                .UserInfor(userCreateResponse)
                .build();
    }


    public List<Loan> getAllLoans() {
        return loanReponsitory.findAll();
    }



}
