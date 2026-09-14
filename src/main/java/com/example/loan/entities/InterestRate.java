package com.example.loan.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="interest_rate")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InterestRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="interest_rate")
    private String interestRate;

    @Column(name="month")
    private String month;
    @PrePersist
    @PreUpdate
    public void formatInterestRate() {
        if (this.interestRate != null && !this.interestRate.trim().endsWith("%")) {
            this.interestRate = this.interestRate.trim() + "%";
        }
    }

}
