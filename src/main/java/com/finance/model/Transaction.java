package com.finance.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private double amount;
    private LocalDate date;
    private String type = "unknown";

    public Transaction() {}

    public Transaction(String description, double amount, LocalDate date, String type) {
        this.description = description;
        this.amount = amount;
        this.date = (date != null) ? date : LocalDate.now();
        this.type = (type != null && !type.trim().isEmpty()) ? type : "unknown";
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = (date != null) ? date : LocalDate.now();
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = (type != null && !type.trim().isEmpty()) ? type : "unknown";
    }
}
