package com.expense.service;

import com.expense.model.Expense;

public interface ExpenseService {

	public Iterable<Expense> getExpenseList();

	public Expense getExpenseById(long id);
	
	public Expense createExpense(Expense exp);
	
	public Expense updateExpense(long id, Expense exp);
	
	public Expense deleteExpenseById(long id);

}
