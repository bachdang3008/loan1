package com.example.loan.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "Loan")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "loan_amount")
    private double loanAmount;

    @Column(name = "month")
    private String month;

    @Column(name = "interest_rate")
    private double interestRate;

    @Column(name = "amount_payable")
    private double amountPayable;

    @Column(name = " Monthly_payment_amount" )
    private double MonthlyPaymentAmount;


    @Column(name = "create_date")
    private LocalDate createDate;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @Column(name = "id_cccd")
    private String cccd;


    @OneToOne
    @JoinColumn(name = "user_id")
    private User userid;

    @Column(name = "status")
    private String status;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDate createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDate updatedAt;


}
