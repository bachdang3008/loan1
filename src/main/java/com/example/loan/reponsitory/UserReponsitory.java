package com.example.loan.reponsitory;

import com.example.loan.entities.User;
import org.hibernate.sql.Delete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserReponsitory extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);
    List<User> findAll();
    User findById(Long id);
    void deleteById(int id);
    Optional<User> findUsersById(Long id);
}
