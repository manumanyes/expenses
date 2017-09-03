package com.expense.repository;

import org.springframework.data.repository.CrudRepository;

import com.expense.model.Expense;

public interface ExpenseRepository extends CrudRepository<Expense, Long> {


}
