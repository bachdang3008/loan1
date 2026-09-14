package com.example.loan.response;
import com.example.loan.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@Builder
@AllArgsConstructor

public class LoanCreateResponse {
    private String loanAmount;
    private String month;
    private String cccd;
    private double interestRate ;
    private double amountPayable ;
    private double monthlyPaymentAmount;
    private LocalDate createDate;
    private LocalDate paymentDate ;
    private String status;
    private User user;



}
