package me.mzhli.javaexample.util;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


public class DateTime {

	private Date now = new Date();
	
	public DateTime() {
		// DateFormat for default locale
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
		System.out.printf("Now[default]: %s\n", df.format(now));
		// Change time zone to GMT
		df.setTimeZone(TimeZone.getTimeZone("GMT"));
		System.out.printf("Now[GMT]: %s\n", df.format(now));
		
		// DateFormat for US locale
		DateFormat dfUS = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.MEDIUM, Locale.US);
		System.out.printf("Now[Locale=US]: %s\n", dfUS.format(now));
		
		// Customized DataFormat
		DateFormat dfCustom = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a z", Locale.US);
		// When you create a date by yourself or set a specific time in calendar, you always 
		// need to specify the time zone along with the time value. For example, below we set
		// time to 15:30 with GMT+8. If we format it with GMT-8, then the printed time will be
		// 11:30 yesterday.
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
		cal.set(cal.get(Calendar.YEAR), 
				cal.get(Calendar.MONTH),
				cal.get(Calendar.DAY_OF_MONTH),
				15, 30, 0);
		dfCustom.setTimeZone(TimeZone.getTimeZone("PST"));
		System.out.printf("Custom[TimeZone=PST]: %s\n", dfCustom.format(cal.getTime()));
		dfCustom.setTimeZone(TimeZone.getDefault());
		System.out.printf("Custom[TimzZone=Default]: %s\n", dfCustom.format(cal.getTime()));
	}

	public static void main(String[] args) {
		DateTime dt = new DateTime();
	}

}
