package com.example.loan.reponsitory;

import com.example.loan.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoanReponsitory extends JpaRepository<Loan, Long> {
  Optional<Loan> findById(Long id);


}


