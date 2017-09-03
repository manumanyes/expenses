package com.expense.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.expense.model.Expense;
import com.expense.repository.ExpenseRepository;

@Repository
public class ExpenseDaoImpl implements ExpenseDao {

	@Autowired
	ExpenseRepository repository;

	@Override
	public Expense createExpense(Expense exp) {
		return repository.save(exp);
	}

	@Override
	public Iterable<Expense> getExpenseList() {
		return repository.findAll();
	}

	@Override
	public Expense getExpenseById(long id) {
		return repository.findOne(id);
	}
	
	@Override
	public Expense updateExpense(Expense exp) {
		return repository.save(exp);
	}

	@Override
	public void deleteExpense(Expense exp) {
		repository.delete(exp);
	}


	
}
