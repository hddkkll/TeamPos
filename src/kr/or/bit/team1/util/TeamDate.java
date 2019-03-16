package kr.or.bit.team1.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

enum PeriodType {YEAR, MONTH, WEEK, DAY};

public class TeamDate {
	public static Calendar date(String date) {
		Calendar cal=new GregorianCalendar();
		return cal;
	}
	
	public static String datePrint(Date date) {
		String dateFormat="";
		return dateFormat;
	}
	
	public static String datePrint(String date) {
		String dateFormat="";
		return dateFormat;
	}
	
	public static Calendar dateSet(int year, int month, int day) {
		Calendar cal=new GregorianCalendar();
		return cal;
	}
	
	public static Calendar dateGet(Date date) {
		Calendar cal=new GregorianCalendar();
		return cal;
	}
	
	public static int getYear(String date) {
		int year=0;
		return year;
	}
	
	public static int getMonth(String date) {
		int month=0;
		return month;
	}

	public static int getDay(String date) {
		int day=0;
		return day;
	}

	public static Calendar getYearPlus(String date, int years) {
		Calendar cal=new GregorianCalendar();
		return cal;
	}

	public static Calendar getYearMinus(String date, int years) {
		Calendar cal=new GregorianCalendar();
		return cal;
	}
	
	public static Calendar getMonthPlus(String date, int months) {
		Calendar cal=new GregorianCalendar();
		return cal;
	}

	public static Calendar getMonthMinus(String date, int months) {
		Calendar cal=new GregorianCalendar();
		return cal;
	}

	public static Calendar getDayPlus(String date, int days) {
		Calendar cal=new GregorianCalendar();
		return cal;
	}

	public static Calendar getDayMinus(String date, int days) {
		Calendar cal=new GregorianCalendar();
		return cal;
	}
	
	public static int getDayOfWeek(String date) {
		int weekday=0;
		return weekday;
	}

	public static String getDayOfWeekString(String date) {
		String weekday="";
		return weekday;
	}
	
	public static int getLastDayOfMonth(String date) {
		int lastDay=0;
		return lastDay;
	}

	public static int getPeriod(String fromDate, String toDate, PeriodType pType) {
		int period=0;
		return period;
	}
	
	public static int dayCount(String fromDate, String toDate) {
		int days=0;
		return days;
	}
	
	public static int daysOfYear(String fromDate, String toDate) {
		int days=0;
		return days;
	}
	
	public static boolean isEndOfMonth(String date) {
		boolean isEnd=false;
		return isEnd;
	}
	
	public static boolean isLeapYear(String date) {
		boolean isLeap=false;
		return isLeap;
		
	}
	 public static String DateString(Calendar date) {
	        return date.get(Calendar.YEAR) + "년" + (date.get(Calendar.MONTH) + 1) + "월" + date.get(Calendar.DATE) + "일";
	    }

	    public static String DateString(Calendar date, String opr) {
	        return date.get(Calendar.YEAR) + opr + (date.get(Calendar.MONTH) + 1) + opr + date.get(Calendar.DATE);
	    }
	

}
