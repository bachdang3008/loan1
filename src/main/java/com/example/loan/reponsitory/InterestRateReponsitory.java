package com.example.loan.reponsitory;

import com.example.loan.entities.InterestRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface InterestRateReponsitory extends JpaRepository<InterestRate, Long> {
    List<InterestRate> findAll();
    void deleteById(int id);
    InterestRate findById(int id);
    Optional<InterestRate> findByMonth(String month);
}
