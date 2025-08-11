package com.finance.controller;

import com.finance.model.Transaction;
import com.finance.service.TransactionService;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable Long id) {
        return transactionService.getTransactionById(id).orElse(null);
    }

    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionService.createTransaction(transaction);
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
    }

    @DeleteMapping
    public void deleteTransactions(@RequestBody List<Long> ids) {
        transactionService.deleteTransactions(ids);
    }

    @DeleteMapping("/month/{year}/{month}")
    public void deleteTransactionsByMonth(@PathVariable int year, @PathVariable int month) {
        transactionService.deleteTransactionsByMonth(year, month);
    }

    @GetMapping("/type/{type}")
    public List<Transaction> getTransactionsByType(@PathVariable String type) {
        return transactionService.getTransactionsByType(type);
    }

    @GetMapping("/date/{date}")
    public List<Transaction> getTransactionsByDate(@PathVariable String date) {
        return transactionService.getTransactionsByDate(LocalDate.parse(date));
    }

    @GetMapping("/between")
    public List<Transaction> geTransactionsBetweenDates(
        @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
        @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
            if (start == null || end == null) {
                throw new IllegalArgumentException("Start and end dates must be provided");
            }
            else if (start.isAfter(end)) {
                throw new IllegalArgumentException("Start date cannot be after end date");
            }

            return transactionService.getTransactionsBetweenDates(start, end);
        }

    @ControllerAdvice
    public class GlobalExceptionHandler {
        @ExceptionHandler(IllegalArgumentException.class)
        @ResponseBody
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public String handleIllegalArgumentException(IllegalArgumentException ex) {
            return ex.getMessage();
        }
    }  
}