package com.expense.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.expense.model.Expense;
import com.expense.service.ExpenseService;

@RestController
public class ExpenseController {

	private static final Logger log = LoggerFactory.getLogger(ExpenseController.class);
	
	@Autowired
	ExpenseService expenseService;

	//List all expenses
    @RequestMapping(value = "/expense/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Expense>> listAllExpenses() {
    	log.info("/expense/ -> listAllExpenses");

    	Iterable<Expense> expenseIterator = expenseService.getExpenseList();

        List<Expense> expenseList = new ArrayList<Expense>();
        
        if(expenseIterator != null) {
        	for(Expense exp: expenseIterator) {
        		expenseList.add(exp);
        	}
        }
        
        if(expenseList.isEmpty()){
        	log.info("There is no expenses");
            return new ResponseEntity<List<Expense>>(HttpStatus.NO_CONTENT);
        }
        
		log.info("Expenses list:");
		log.info("-------------------------------");
        for(int i=0; i < expenseList.size(); i++) {
        	log.info(expenseList.get(i).toString());
        }
        
        return new ResponseEntity<List<Expense>>(expenseList, HttpStatus.OK);
    }
    
    //Get single expense
    @RequestMapping(value = "/expense/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Expense> getExpense(@PathVariable("id") Long id) {
    	log.info("Fetching Expense with id " + id);
        Expense expense = expenseService.getExpenseById(id);
        
        if (expense == null) {
        	log.info("Expense with id " + id + " not found");
            return new ResponseEntity<Expense>(HttpStatus.NOT_FOUND);
        }
        
        log.info("Expense with id " + id + " found: " + expense.toString());
        return new ResponseEntity<Expense>(expense, HttpStatus.OK);
    }
    
    //Create expense
    @RequestMapping(value = "/create/", method = RequestMethod.POST)
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense) {
        log.info("Creating expense " + expense.toString());
        
        Expense exp = expenseService.createExpense(expense);
        if (exp == null) {
            log.info("The expense has not been created");
            return new ResponseEntity<Expense>(expense, HttpStatus.CONFLICT);
        }
        log.info("The expense has been created: " + exp.toString());
 
        return new ResponseEntity<Expense>(exp, HttpStatus.CREATED);
    }
 
    //Update expense
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Expense> updateExpense(@PathVariable("id") long id, @RequestBody Expense expense) {
        log.info("Updating expense " + id + " to " + expense.toString());
         
        Expense updatedExpense = expenseService.updateExpense(id, expense);
         
        if (updatedExpense==null) {
            log.info("Expense with id " + id + " not found");
            return new ResponseEntity<Expense>(HttpStatus.NOT_FOUND);
        }
        
        log.info("Expense updated to: " + updatedExpense.toString());
 
        return new ResponseEntity<Expense>(updatedExpense, HttpStatus.OK);
    }
    
    //Delete expense
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteExpense(@PathVariable("id") long id) {
        log.info("Fetching & expense with id " + id);
 
        Expense expense = expenseService.deleteExpenseById(id);
        if (expense == null) {
            log.info("Unable to delete. expense with id " + id + " not found");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        log.info("Expense deleted: " + expense.toString());

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping("/")
    public RedirectView localRedirect() {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("expense.html");
        return redirectView;
    }

}
