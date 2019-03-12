package kr.or.bit.team1.util;

import java.util.regex.Pattern;

public class TeamPatterns {
	public static boolean iscellPhoneMetPattern(String cellPhoneNumber) {
		boolean isMet ;
		String pattern = "^\\d{2,3}-\\d{3,4}-\\d{4}$";
		isMet = Pattern.matches(pattern, cellPhoneNumber);
		return isMet;
	}
}
