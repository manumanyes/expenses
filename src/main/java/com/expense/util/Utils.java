package com.expense.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Utils {

	private static final DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	
	public static String dateToString(Date date) {
		if(date == null) {
			return "";
		}
		return df.format(date);
	}
	
}
