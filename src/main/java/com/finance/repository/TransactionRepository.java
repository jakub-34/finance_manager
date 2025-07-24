package com.finance.repository;

import com.finance.model.Transaction;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByType(String type);
    List<Transaction> findByDate(LocalDate date);
    List<Transaction> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
