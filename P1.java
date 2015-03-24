package week1Problems;

public class P1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/** 
		 * Make a loop that runs as long as its below or equal to 1000
		 * Have a variable to find the sum that starts at 0
		 * As it runs through the loop if the number is a multiple of 3 or 5 add it to the variable
		 * Print the variable's new value
		 */
		
		int sum = 0;
		for (int i = 0; i < 1000; i++) {
			if (i % 5 == 0 || i % 3 == 0) {
				sum += i;
			}
		}
		System.out.println("The sum is: " + sum);
	
	}

}
