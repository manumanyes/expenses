// tag::sample[]
package com.expense.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.expense.util.Utils;

@Entity
public class Expense {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    
    private String description;
    
    private double value;
    
    private Date date;

    protected Expense() {}

    
    public Expense(String description, double value, Date date) {
    	super();
        this.description = description;
        this.value = value;
        this.date = date;
    }


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}	
    
    @Override
    public String toString() {
        return String.format(
                "Expense [id=%d, description='%s', value='%s', date=%s]",
                id, description, value, Utils.dateToString(date));
    }


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Expense other = (Expense) obj;
		if (id != other.id)
			return false;
		return true;
	}

}

