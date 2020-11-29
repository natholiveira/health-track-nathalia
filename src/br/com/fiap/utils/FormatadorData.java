package br.com.fiap.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FormatadorData {
	 public static String toDate(Date data) {
        if (data==null) {
            data = new Date();
        }

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(data);
     }

    public static Date parseDate(String data) {
   
        Date date1;
		try {
			date1 = new SimpleDateFormat("yyyy-M-dd").parse(data);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return new Date();
		}
        return date1;
   
    }
    
    public static java.sql.Date toDateSql(String data) {
    	Date date = null;
    	try {
			date = new SimpleDateFormat("dd/MM/yyyy").parse(data);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  
        return new java.sql.Date(date.getTime());
  
    }
    
    public static java.sql.Date toDateTimeSql(String data) {
    	Date date = null;
    	try {
			date = new SimpleDateFormat("dd/MM/yyyy").parse(data);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  
        return new java.sql.Date(date.getTime());
  
    }
    
    
    public static Date parseDateTime(String data) {
    	   
    	String dataReplace = data.replace("T", " ");
    	
        Date date1;
		try {
			date1 = new SimpleDateFormat("yyyy-M-dd HH:mm").parse(dataReplace);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return new Date();
		}
        return date1;
   
    }
    
    public static String toDateTime(Date data) {
        if (data==null) {
        	data = new Date();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return df.format(calendar.getTime());
    }

}
