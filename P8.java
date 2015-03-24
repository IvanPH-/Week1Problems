package week1Problems;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/*###Counting Minutes

Build a class that prompts a user to input some time in 12 hour format (e.g. 9:00am), then compares that time to the actual current time, then prints the difference (in minutes) to the console. For example, if a user inputs `9:16am` and it's really 9:10am, the console would read `+6 minutes`. If the user inputs `9:16am` and it's really 9:20am, the console should read `-4 minutes`.

**Save your code as `p8.java` in the `answers` directory.**
*/

public class P8 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter a time");
		String timeCompare = input.nextLine();
		getTimeDifference(timeCompare);
	}
	
	public static void getTimeDifference(String x) {
		String[] time = x.split(":");
		Calendar calendar = new GregorianCalendar();
	    String am_pm;
	    int hour = calendar.get( Calendar.HOUR );
	    int minute = calendar.get( Calendar.MINUTE );
	    System.out.println((Integer.parseInt(time[0]) - hour) + " hours and " + (Integer.parseInt(time[1]) - minute) + " minutes away");
	}
}
