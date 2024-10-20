package com.example.expensecalculator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.expensecalculator.entity.Expense;
import com.example.expensecalculator.repository.ExpenseRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public Expense addExpense(Expense expense) {
        expense.setDate(LocalDate.now());  // Set current date
        return expenseRepository.save(expense);
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public double calculateTotalExpenses() {
        return expenseRepository.findAll()
                .stream()
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    // Calculate daily total expenses
    public double calculateDailyTotalExpenses(LocalDate date) {
        return expenseRepository.findAll()
                .stream()
                .filter(expense -> expense.getDate().isEqual(date))
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    // New method to calculate monthly total expenses
    public double calculateMonthlyTotalExpenses(int year, int month) {
        return expenseRepository.findAll()
                .stream()
                .filter(expense -> expense.getDate().getYear() == year && expense.getDate().getMonthValue() == month)
                .mapToDouble(Expense::getAmount)
                .sum();
    }
}
