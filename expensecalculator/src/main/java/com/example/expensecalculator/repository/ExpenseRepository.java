package com.example.expensecalculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.expensecalculator.entity.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
