package govt.vaccination.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public interface DateUtils {
	final static Log logger = LogFactory.getLog(DateUtils.class);
	
	public static Date getTodayDate() throws ParseException {
	Calendar currentDate = Calendar.getInstance();
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date todayDate = (Date) formatter.parse(formatter.format(currentDate.getTime()));


	//Date todayDate = (Date)formatter.parse(formatter.format(currentDate.getTime()));
	
	// System.out.println("Current time of the day using Calendar - 24 hour format:
	// "+ formattedDate);
	return todayDate;

	}

	public static Date getCurrentDate() throws ParseException {

		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		currentDate.set(Calendar.HOUR_OF_DAY, 0);
		currentDate.set(Calendar.MINUTE, 0);
		currentDate.set(Calendar.SECOND, 0);

		Date todayDate = (Date) formatter.parse(formatter.format(currentDate.getTime()));

		return todayDate;

	}

	public static Date getDateWithFormat(Date date) throws ParseException {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		Date givenDate = (Date) formatter.parse(formatter.format(date));
		return givenDate;

	}

    public static Date getMonthAndYearFormat(Date date) throws ParseException{
         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
         SimpleDateFormat formatterObj = new SimpleDateFormat("yyyy-MM-dd");
         String  dateString = formatter.format(date);

         dateString = "05-" + dateString;
         Date formattedDate = (Date) formatterObj.parse(dateString);
         return formattedDate;

}

       public static Date getDateFromStringFormat(String scheduleDate) throws ParseException{
	    DateFormat formatter = null;
        Date convetedDate = null;
        formatter = new SimpleDateFormat("dd-MM-yyyy");
        convetedDate = (Date) formatter.parse(scheduleDate);
        logger.info("Date from dd-MM-yyyy String in Java :" + convetedDate);
	   return convetedDate;

	}
       
       public static Date getDateFromDateFormat(String scheduleDate) throws ParseException{
   	    DateFormat formatter = null;
           Date convetedDate = null;
           formatter = new SimpleDateFormat("yyyy-MM-dd 'T' HH:mm:ss.SSS'Z'");
           convetedDate = (Date) formatter.parse(scheduleDate);
           logger.info("Date from dd-MM-yyyy String in Java :" + convetedDate);
   	   return convetedDate;

   	}
       
       
       public static Date getDateFormat() throws ParseException {
   		Calendar currentDate = Calendar.getInstance();

   		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");

   		
   		return (Date)formatter.parse(formatter.format(currentDate.getTime()));

   	}
       
       public static Date getDob(Date date) {
    		Calendar c= Calendar.getInstance();
    		c.setTime(date);
    		c.add(Calendar.DATE, 1);
    		Date dob = c.getTime();
    		return dob;

    	   
       }
       
       public static boolean sameDate(Date fromDate,Date toDate) throws ParseException {

      		SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
                if(fmt.format(fromDate).equals(fmt.format(toDate))){
                	return true;
                }
      		
      		return false;

   
}

       public static boolean sameDayWithTime(Date fromDate, Date toDate) {
    	   
    	   long diff = toDate.getTime() - fromDate.getTime();
    	   long diffHours = diff / (60 * 60 * 1000);
    	   
    	   if(diffHours <= 6) {
    		   return true;
    	   }
    	   return false;
    	   
       }
       
       public static boolean isNotEmpty(List list)  {

     	boolean isNotEmpty = false;
     	if(list != null && !list.isEmpty() ) {
     		isNotEmpty = true;
     		
     		
               }
     		
     		return isNotEmpty;

  
}
       
   
   
       
    	   
       
       public static boolean isvalidString(String stringValue)  {

        	boolean isvalidString = false;
        	if(stringValue != null && !stringValue.isEmpty() ) {
        		isvalidString = true;
        }
        		
        		return isvalidString;

     
   }
       
   
       
       public static String generateRandomString() {
    	   String SALTCHARS = "ABCDEFGHIJKLMNOQSTUVWXYZ1234567890";
    	   StringBuilder salt = new StringBuilder();
		   Random rnd =new Random();

    	   while(salt.length()< 4) {
    		   int index =(int) (rnd.nextFloat()* SALTCHARS.length());
    		   salt.append(SALTCHARS.charAt(index));
    		   
    	   }
       String saltStr = SALTCHARS.toString();
       return saltStr;
       
       } 
       
       
       
              
       
       public static Date addDays(Date date,int days) {
    		//Calendar cal = Calendar.getInstance();

    	  
    	   GregorianCalendar gcal = new GregorianCalendar();
    	   gcal.setTime(date);
    	   gcal.add(Calendar.DATE,days);	   
    	   
       return gcal.getTime();
       }

	public static Date minusDays(Date date, int days) {
 	   GregorianCalendar gcal = new GregorianCalendar();
 	   gcal.setTime(date);
 	   gcal.add(Calendar.DATE, -days);

		return gcal.getTime();
	}
       }
       
