package week1Problems;

import java.util.ArrayList;
import java.util.List;

/*
 * ###Consecutive Numbers


**Part 1:** Build your own array of all of the prime numbers up to 100, and store that array in a text file `primearray.txt` in the `answers` directory. **Save your code as `p7a.java` in the `answers` directory**.

**Part 2:** Have the class ` Consecutive` take an array of integers from `primearr.txt` and return the minimum number of integers needed to make the contents of the array consecutive from the lowest number to the highest number. For example: If `arr` contains `[4, 8, 6]` then the output should be 2, because two numbers need to be added to the array (5 and 7) to make it a consecutive array of numbers from 4 to 8. Print out the answers for arrays of the first 5 primes, the first 10 primes, the first 50 primes, and the first 100 prime numbers on separate lines.
**Save your code as `p7b.java` in the `answers` directory.**

 */
public class P7 {
	public static void main(String[] args) {
		int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};
		consecutive(primes, 24);
		consecutive(primes, 5);
		consecutive(primes, 10);
		consecutive(primes, 15);
	}
	
	public static void consecutive(int[] x, int y) {
		//Y being the array number to do this too
		if(y >= x.length) {
			System.err.print("Number not valid, maximum number entered can only be " + (x.length - 1));
			System.exit(1);
		}
		List<Integer> neededNums = new ArrayList<>();
		int counter = 0;
		for(int i = 0; i < y; i++) {
			for(int j = 1; j <= 100; j++) {
				if (x[i] + j == x[i + 1]) {
					break;
				}
				else {
					counter++;
					neededNums.add(x[i] + j);
					}
			}
		}
		System.out.println("The number of numbers needed to make it consecutive to " + x[y] + " were: " + counter + "\n" + "It needed the numbers " + neededNums);
	}
}
