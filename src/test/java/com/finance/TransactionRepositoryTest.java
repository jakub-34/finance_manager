package com.finance;

import com.finance.model.Transaction;
import com.finance.repository.TransactionRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class TransactionRepositoryTest {
	
    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    void testSaveAndFindByType() {
        Transaction t1 = new Transaction("Pizza", 10.5, LocalDate.now(), "food");
        Transaction t2 = new Transaction("Burger", 8.0, LocalDate.now(), "food");
        Transaction t3 = new Transaction("Bus ticket", 2.0, LocalDate.now(), "transport");
    
        transactionRepository.save(t1);
        transactionRepository.save(t2);
        transactionRepository.save(t3);

        List<Transaction> foodTransactions = transactionRepository.findByType("food");

        assertThat(foodTransactions).hasSize(2);
        assertThat(foodTransactions).contains(t1, t2);
        assertThat(foodTransactions.get(0).getType()).isEqualTo("food");
        assertThat(foodTransactions.get(1).getType()).isEqualTo("food");
    }
}