package week1Problems;

import java.util.Arrays;

public class P6 {
	/* ### Poker Hands
	 * Part 1: The file poker.txt in the resources directory. contains one-thousand random hands dealt to two players. Each line of the file contains ten cards (separated by a single space): the first five are Player 1's cards and the last five are Player 2's cards. You can assume that all hands are valid (no invalid characters or repeated cards), each player's hand is in no specific order, and in each hand there is a clear winner. How many hands does player 1 win? Print the answer to the console and save your class in the answers directory as p6a.java.

Part 2: Imagine that there was a third player. Generate a possible hand for the third player given a standard deck with no Jokers, and given that no duplicate cards exist in the deck, and given the cards that Players 1 and 2 already have in their hands (i.e. each new hand should be generated from decks that do not contain the cards held in Player 1's or Player 2's hands). Add Player 3's cards to poker.txt, and save the new file as pokernew.txt in the answers directory. Save your code for this section as p6b.java.

Part 3: If we look at pokernew.txt instead of poker.txt, how many hands does player1 win now? Modify p6a.java from Part 1 to ask the user for an input (ask if there are 2 or 3 players in the game). Print the number of games Player 1 wins in each situation to the console. Save this modified code as p6c.java in the answers directory.
	 */
	public static void main(String[] args) {
		int[] a = {2, 14, 14, 12, 8};
		int[] b = {5, 14, 13, 2, 12};
		Arrays.sort(a);
		Arrays.sort(b);
		boolean[] player1Boolean = new boolean[8];
		boolean[] player2Boolean = new boolean[8];
		compare(a, b, player1Boolean, player2Boolean);
	}
	
	public static void compare(int[] x, int[] y, boolean[] a, boolean[] b) {
		checkPair(x, a);
		checkPair(y, b);
		compareHands(x, y, a, b);
		//Called after comparing both hands values if neither passes in another method somewhere compareHigh(x, y);
	}
	
	private static void compareHands(int[] x, int[] y, boolean[] a, boolean[] b) {
		
		
	}

	private static boolean checkPair(int[] x, boolean[] y) {
		for(int i = 0; i < x.length - 1; i++) {
			if(x[i] == x[i + 1]) {
				return y[0] = true;
			}
		}
		return y[0] = false;
	}
		
	private static void compareHigh(int[] x, int[] y) {
		int player1High = findHighCard(x);
		int player2High = findHighCard(y);
		if (player1High > player2High) {
			System.out.print("Player 1 wins");
		}
		else if (player1High == player2High) {
			compareNextHigh(x, y);
		}
		else {
			System.out.print("Player 2 wins");
		}
	}

	private static void compareNextHigh(int[] x, int[] y) {
		for(int i = x.length - 2; i >= 0; i--) {
			if (x[i] > y[i]) {
				System.out.print("Player 1 wins");
				break;
			}
			else if (x[i] == y[i]) {
				continue;
			}
			else {
				System.out.print("Player 2 wins");
				break;
			}
		}
		
	}

	private static int findHighCard(int[] x) {
		int highNum = x.length - 1;
		return x[highNum];
	}
	
}
