package week1Problems;

import java.text.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
/*###Counting Minutes

Build a class that prompts a user to input some time in 12 hour format (e.g. 9:00am), then compares that time to the actual current time, then prints the difference (in minutes) to the console. For example, if a user inputs `9:16am` and it's really 9:10am, the console would read `+6 minutes`. If the user inputs `9:16am` and it's really 9:20am, the console should read `-4 minutes`.

**Save your code as `p8.java` in the `answers` directory.**
*/

public class P8 {
	public static void main(String[] args) throws ParseException {
		System.out.println("Input a time in the format: YYYY-MM-DD hh-mm am/pm");
		Scanner input = new Scanner(System.in);
		String findTime = input.nextLine();
		getTimeDiff(findTime);
	}
	public static void getTimeDiff(String x) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
		Date timeCompare = sdf.parse(x);
		Calendar c = Calendar.getInstance();
		c.setTime(timeCompare);
		c.set(Calendar.YEAR, 2015);
		timeCompare = c.getTime();
		Date timeNow = new Date();
		System.out.println("Current time is: " + sdf.format(timeNow));
		System.out.println("You entered: " + sdf.format(timeCompare));
	
		long minutes = (long)(timeCompare.getTime() - timeNow.getTime()) / (60 * 1000) % 60;
		System.out.println("Difference in minutes: " + minutes);
	}
}