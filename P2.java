package week1Problems;

public class P2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/**
		 * Create a loop that runs until x reaches 4 million
		 * As the loop counts have another variable that continues the sequence
		 * First check if the number is %2 = 0, or even.
		 * Add all even numbers and return the value
		 */
		long sum = 0;
		int x = 4000000;
		int[] fibo = new int[x];
		fibo[0] = 0;
		fibo[1] = 1;
		for (int i = 2; fibo[i - 1] < x; i++) {
			fibo[i] = fibo[i - 1] + fibo[i - 2];
			if (fibo[i] > x) {
				break;
			}
			if (fibo[i] % 2 == 0) {
				sum += fibo[i];
			}
			System.out.println(fibo[i]);
		}
	
		System.out.println("The sum is: " + sum);
	}

}
