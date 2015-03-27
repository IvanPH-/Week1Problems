package week1Problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class P6 {
	/* ### Poker Hands
	 * Part 1: The file poker.txt in the resources directory. contains one-thousand random hands dealt to two players. Each line of the file contains ten cards (separated by a single space): the first five are Player 1's cards and the last five are Player 2's cards. You can assume that all hands are valid (no invalid characters or repeated cards), each player's hand is in no specific order, and in each hand there is a clear winner. How many hands does player 1 win? Print the answer to the console and save your class in the answers directory as p6a.java.

Part 2: Imagine that there was a third player. Generate a possible hand for the third player given a standard deck with no Jokers, and given that no duplicate cards exist in the deck, and given the cards that Players 1 and 2 already have in their hands (i.e. each new hand should be generated from decks that do not contain the cards held in Player 1's or Player 2's hands). Add Player 3's cards to poker.txt, and save the new file as pokernew.txt in the answers directory. Save your code for this section as p6b.java.

Part 3: If we look at pokernew.txt instead of poker.txt, how many hands does player1 win now? Modify p6a.java from Part 1 to ask the user for an input (ask if there are 2 or 3 players in the game). Print the number of games Player 1 wins in each situation to the console. Save this modified code as p6c.java in the answers directory.
	 */
	public static void main(String[] args) {
		List<Integer> a = new ArrayList<>();
		a.add(2);
		a.add(14);
		a.add(14);
		a.add(12);
		a.add(8);
		Collections.sort(a);
		
		List<Integer> b = new ArrayList<>();
		b.add(5);
		b.add(15);
		b.add(15);
		b.add(2);
		b.add(12);
		Collections.sort(b);
		boolean[] player1Boolean = new boolean[8];
		boolean[] player2Boolean = new boolean[8];
		compare(a, b, player1Boolean, player2Boolean);
	}
	
	public static void compare(List<Integer> x, List<Integer> y, boolean[] a, boolean[] b) {
		checkPairSeries(x, a);
		checkPairSeries(y, b);
		compareHands(x, y, a, b);
		//Called after comparing both hands values if neither passes in another method somewhere compareHigh(x, y);
	}
	
	private static void checkPairSeries(List<Integer> x, boolean[] y) {
		List<Integer> hold = new ArrayList<>();
		for(int i = 0; i <= x.size() - 2; i++) {
			if(x.get(i) == x.get(i + 1)) {
				if(y[0] == true) {
					y[0] = false;
					y[1] = true;
				}
				hold.add(x.get(i));
				hold.add(x.get(i + 1));
				x.remove(x.get(i));
				x.remove(x.get(i));
				y[0] = true;
			}
		}
		x.addAll(hold);
		Collections.sort(x);
		hold.clear();
	}
		
	private static void compareHigh(List<Integer> x, List<Integer> y) {
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

	private static void compareNextHigh(List<Integer> x, List<Integer> y) {
		for(int i = x.size() - 2; i >= 0; i--) {
			if (x.get(i) > y.get(i)) {
				System.out.print("Player 1 wins");
				break;
			}
			else if (x.get(i) == y.get(i)) {
				continue;
			}
			else {
				System.out.print("Player 2 wins");
				break;
			}
		}
		
	}

	private static int findHighCard(List<Integer> x) {
		int highNum = x.size() - 1;
		return x.get(highNum);
	}

	private static void compareHands(List<Integer> x, List<Integer> y, boolean[] a, boolean[] b) {
		int buildValue1 = findValue(a);
		int buildValue2 = findValue(b);
		if(buildValue1 > buildValue2) {
			System.out.print("Player 1 wins");
		}
		else if(buildValue1 == buildValue2) {
			compareHigh(x, y);
		}
		else {
			System.out.print("Player 2 wins");
		}
	}
	
	private static int findValue(boolean[] x) {
		int value = 0;
		for(int i = 0; i <= x.length - 1; i++) {
			if (x[i] == true) {
				value += i + 1;
				break;
			}
		}
		return value;
	}
}