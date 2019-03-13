package kr.or.bit.team1.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class TeamFormat {
	public static boolean iscellPhoneMetPattern(String cellPhoneNumber) {
		boolean isMet ;
		String pattern = "^\\d{2,3}-\\d{3,4}-\\d{4}$";
		isMet = Pattern.matches(pattern, cellPhoneNumber);
		return isMet;
	}
	
	public static String dateTimeFormat(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		String dateTime=df.format(date);
		return dateTime;
		
	}
}
