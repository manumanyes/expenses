package com.expense.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expense.dao.ExpenseDao;
import com.expense.model.Expense;


@Service("expenseService")
public class ExpenseServiceImpl implements ExpenseService {
	
	private static final Logger log = LoggerFactory.getLogger(ExpenseServiceImpl.class);

	@Autowired
	ExpenseDao dao;
	
	@Override
	public Iterable<Expense> getExpenseList() {
		System.out.print("getExpenseList");
		return dao.getExpenseList();
	}

	@Override
	public Expense getExpenseById(long id) {
		return dao.getExpenseById(id);
	}

	@Override
	public Expense createExpense(Expense exp) {
		return dao.createExpense(exp);
	}	

	@Override
	public Expense updateExpense(long id, Expense exp) {
		Expense expense = getExpenseById(id);
		if(expense != null) {
			log.info("Expense to be updated: " + expense.toString());
			exp.setId(id);
			dao.updateExpense(exp);
		}
		
		return expense;
	}	

	@Override
	public Expense deleteExpenseById(long id) {
		Expense expense = getExpenseById(id);
		if(expense != null) {
			log.info("Expense to be deleted: " + expense.toString());
			dao.deleteExpense(expense);
		}
		return expense;
	}
	
}
