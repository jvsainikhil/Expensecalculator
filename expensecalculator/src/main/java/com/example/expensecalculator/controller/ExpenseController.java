package com.example.expensecalculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.expensecalculator.entity.Expense;
import com.example.expensecalculator.service.ExpenseService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    // Add a new expense
    @PostMapping
    public ResponseEntity<Expense> addExpense(@RequestBody Expense expense) {
        Expense newExpense = expenseService.addExpense(expense);
        return ResponseEntity.ok(newExpense);
    }

    // Get all expenses
    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses() {
        List<Expense> expenses = expenseService.getAllExpenses();
        return ResponseEntity.ok(expenses);
    }

    // Calculate total expenses
    @GetMapping("/total")
    public ResponseEntity<Double> getTotalExpenses() {
        double total = expenseService.calculateTotalExpenses();
        return ResponseEntity.ok(total);
    }

    // Calculate total expenses for a specific day
    @GetMapping("/total/{date}")
    public ResponseEntity<Double> getDailyTotalExpenses(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date); // Convert String to LocalDate
        double total = expenseService.calculateDailyTotalExpenses(localDate);
        return ResponseEntity.ok(total);
    }
    

    // Calculate total expenses for a specific month and year
    @GetMapping("/total/month")
    public ResponseEntity<Double> getMonthlyTotalExpenses(@RequestParam int year, @RequestParam int month) {
        double total = expenseService.calculateMonthlyTotalExpenses(year, month);
        return ResponseEntity.ok(total);
    }
}
