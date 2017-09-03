package com.expense.dao;

import com.expense.model.Expense;

public interface ExpenseDao  {
	
	public Expense createExpense(Expense exp);

	public Iterable<Expense> getExpenseList();
	
	public Expense getExpenseById(long id);
	
	public Expense updateExpense(Expense exp);

	public void deleteExpense(Expense exp);
	
}
