package week1Problems;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class P6 {
	static int wins = 0;
	/* ### Poker Hands
	 * Part 1: The file poker.txt in the resources directory. contains one-thousand random hands dealt to two players. Each line of the file contains ten cards (separated by a single space): the first five are Player 1's cards and the last five are Player 2's cards. You can assume that all hands are valid (no invalid characters or repeated cards), each player's hand is in no specific order, and in each hand there is a clear winner. How many hands does player 1 win? Print the answer to the console and save your class in the answers directory as p6a.java.

Part 2: Imagine that there was a third player. Generate a possible hand for the third player given a standard deck with no Jokers, and given that no duplicate cards exist in the deck, and given the cards that Players 1 and 2 already have in their hands (i.e. each new hand should be generated from decks that do not contain the cards held in Player 1's or Player 2's hands). Add Player 3's cards to poker.txt, and save the new file as pokernew.txt in the answers directory. Save your code for this section as p6b.java.

Part 3: If we look at pokernew.txt instead of poker.txt, how many hands does player1 win now? Modify p6a.java from Part 1 to ask the user for an input (ask if there are 2 or 3 players in the game). Print the number of games Player 1 wins in each situation to the console. Save this modified code as p6c.java in the answers directory.
	 */
	
	//Add a check for flush
	public static void main(String[] args) throws IOException {
		// Open the file
		FileInputStream fstream = new FileInputStream("src/week1Problems/poker.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		String strLine;
		

		//Read File Line By Line
		while ((strLine = br.readLine()) != null)   {
		  // Print the content on the console
		  runHandConstructor(strLine);
		}
		//Close the input stream
		br.close();
		
		System.out.print("Player 1 has won: " + wins + " games");
	}
		
	private static void runHandConstructor(String x) {
			List<String> dataArray = new ArrayList<>();
			List<String> dataArray2 = new ArrayList<>();
			dataConstructor(x, dataArray, dataArray2);
			String listToString = charArrayPrep(dataArray);
			String listToString2 = charArrayPrep(dataArray2);
			char[] playerHand1 = listToString.toCharArray();
			char[] playerHand2 = listToString2.toCharArray();
			List<Character> play1 = new ArrayList<>();
			List<Character> play2 = new ArrayList<>();
			constructPlayerList(play1, playerHand1);
			constructPlayerList(play2, playerHand2);
			runPoker(play1, play2);
		}
	
	private static void constructPlayerList(List<Character> x, char[] y) {
		for(int i = 0; i <= y.length - 1; i++) {
			x.add(y[i]);
		}
		
	}

	private static String charArrayPrep(List<String> x) {
		String toReturn = "";
		for(int i = 0; i <= x.size() - 1; i++) {
			 toReturn += x.get(i);
			}
		return toReturn;
	}
	
	private static void dataConstructor(String x, List<String> y, List<String> z) {
		boolean hand2 = false;
		String[] placeHold = x.split(" ");
		if(hand2 == false) {
			for(int i = 0; i <= 4; i++) {
				y.add(placeHold[i]);
			}
			hand2 = true;
			}
		if (hand2 == true) {
			for(int i = 5; i <= placeHold.length - 1; i++) {
				z.add(placeHold[i]);
			}
			hand2 = false;
		}
	}
	
	private static void runPoker(List<Character> x, List<Character> y) {
		boolean[] player1Boolean = new boolean[8];
		boolean[] player2Boolean = new boolean[8];
		List<Integer> a = cleanHands(player1Boolean, x);
		List<Integer> b = cleanHands(player2Boolean, y);
		Collections.sort(a);
		Collections.sort(b);
		compare(a, b, player1Boolean, player2Boolean);
	}
	
	private static List<Integer> cleanHands(boolean[] y, List<Character> z) {
		List<Character> flushFinder = new ArrayList<>();
		for(int i = 0; i <= z.size() - 1; i++) {
			if(z.get(i) == 'C' || z.get(i) == 'H' || z.get(i) == 'D' || z.get(i) == 'S') {
				flushFinder.add(z.get(i));
				z.remove(i);
				i = 0;
			}
		}
		findFlush(flushFinder, y);
		//Another method here
		List<Integer> toReturn = remainingCharsToInt(z);
		return toReturn;
	}

	private static List<Integer> remainingCharsToInt(List<Character> x) {
		List<Integer> toReturn = new ArrayList<>();
		List<String> newList = new ArrayList<>();
		
		for(int i = 0; i <= x.size() - 1; i++){
			newList.add(String.valueOf(x.get(i)));
			switch(newList.get(i)) {
				case "T":
						newList.remove(i);
						newList.add("10");
						break;
					case "J":
						newList.remove(i);
						newList.add("11");
						break;
					case "Q":
						newList.remove(i);
						newList.add("12");
						break;
					case "K":
						newList.remove(i);
						newList.add("13");
						break;
					case "A":
						newList.remove(i);
						newList.add("14");
						break;
				}
			toReturn.add(Integer.parseInt(newList.get(i)));
		}
		
		return toReturn;
	}

	private static void findFlush(List<Character> x, boolean[] y) {
		for(int i = 0; i <= x.size() - 2; i++) {
			if(x.get(i) == x.get(i + 1)) {
				y[4] = true;
			}
			else {
				y[4] = false;
				break;
			}
		}
		
	}

	public static void compare(List<Integer> x, List<Integer> y, boolean[] a, boolean[] b) {
		checkOfAKindSeries(x, a);
		checkOfAKindSeries(y, b); 
		//If statment in a method to pass both a, b to make sure fullhouse(which we checked for), and four of a kind aren't possibilities. If they aren't we run to check for pairs
		checkToCheckPair(x, a);
		checkToCheckPair(y, b);
		//If statement to check for straights if NONE of the values aside from flush which is an exception is false. If false checks for straight
		checkToCheckStraight(x, a);
		checkToCheckStraight(y, b);
		compareHands(x, y, a, b);
	}
	
	private static void checkToCheckStraight(List<Integer> x, boolean[] y) {
		boolean flagToCheck = true;
		for(int i = 0; i <= y.length - 1; i++) {
			if(i == 4) {
				continue;
			}
			else if(y[i] == true) {
				flagToCheck = false;
				break;
			}
		}
		if (flagToCheck == true) {
			checkStraight(x, y);
		}
	}
	
	private static void checkToCheckPair(List<Integer> x, boolean[] y) {
		if (y[2] != true && y[5] != true && y[6] != true) {
			checkPairSeries(x, y);
		}
	}
	
	private static void checkStraightFlushSeries(boolean[] y) {
		if(y[3] == true && y[4] == true) {
			y[3] = false;
			y[4] = false;
			y[7] = true;
		}
	}
	
	private static void checkFullHouse(boolean[] y) {
		if (y[0] == true && y[2] == true) {
			y[0] = false;
			y[2] = false;
			y[5] = true;
		}
	}
	
	private static void checkStraight(List<Integer> x, boolean[] y) {
		List<Integer> placeHold = new ArrayList<>(x);
		Collections.sort(x); //Return it to how it was originally before being sorted - Done
		for(int i = 0; i <= x.size() - 2; i++) {
			if (x.get(i) + 1 != x.get(i + 1)) {
				y[3] = false;
				break;
			}
			else {
				y[3] = true;
				continue;
			}
		}
		if(y[3] == false) {
			x = placeHold;
		}
		else {
			checkStraightFlushSeries(y);
		}
	}
	
	private static void checkOfAKindSeries(List<Integer> x, boolean[] y) {
		List<Integer> placeHold = new ArrayList<>(x);
		List<Integer> holdRemove = new ArrayList<>();
		Collections.sort(x); //Return it to how it was originally before being sorted if none are true. Otherwise maintain the three or four at the end if true - Done
		//Check for pairs in here if three of a kind passes
		boolean threeFlag = false;
		boolean fourFlag = false;
		for(int i = 0; i <= x.size() - 2; i++) {
			if(x.get(i) == x.get(i + 1)) {
				if(threeFlag == true) {
					threeFlag = false;
					fourFlag = true;
					holdRemove.add(x.get(i));
					holdRemove.add(x.get(i + 1));
					holdRemove.add(x.get(i - 1));
					y[2] = true;
					continue;
				}
				else if(fourFlag == true) {
					holdRemove.add(x.get(i + 1));
					y[2] = false;
					y[6] = true;
					break;
				}
				threeFlag = true;
			}
			else if(x.get(i) != x.get(i + 1)) {
				threeFlag = false;
				fourFlag = false;
			}
		}
		threeFlag = false;
		
		//Remove three pair if true in y. check two for pair by calling pair check.
		if(y[2] == true) {
			removeForPairCheck(x, holdRemove);
			checkPairSeries(x, y);
			repairComparison(x, holdRemove);
		}
		else if(y[6] == true) {
			y[0] = false;
			y[1] = false;
		}
		
		checkFullHouse(y);
		
		if(fourFlag != true) {
			x = placeHold;
		}
		else if(fourFlag == true) {
			//run code to remove the matches from x, and place them at the end
			repairComparison(x, holdRemove);
		}
		
	}
	
	private static void removeForPairCheck(List<Integer> x, List<Integer> y) {
		for(int i = 0; i <= x.size() - 1; i++) {
			for(int j = 0; j <= x.size() - 1; j++) {
				if(y.get(i) == x.get(j)) {
					x.remove(j);
					j = 0;
				}
			}
		}
	}
	
	private static void repairComparison(List<Integer> x, List<Integer> y) {
		for(int i = 0; i <= x.size() - 1; i++) {
			for(int j = 0; j <= x.size() - 1; j++) {
				if(y.get(i) == x.get(j)) {
					x.remove(j);
					j = 0;
				}
			}
		}
		x.addAll(y);
	}
	
	private static void checkPairSeries(List<Integer> x, boolean[] y) {
		List<Integer> hold = new ArrayList<>();
		for(int i = 0; i <= x.size() - 2;) {
			if(x.get(i) == x.get(i + 1)) {
				if(y[0] == true) {
					y[0] = false;
					y[1] = true;
					hold.add(x.get(i));
					hold.add(x.get(i + 1));
					x.remove(x.get(i));
					x.remove(x.get(i));
					i = 0;
					continue;
				}
				hold.add(x.get(i));
				hold.add(x.get(i + 1));
				x.remove(x.get(i));
				x.remove(x.get(i));
				y[0] = true;
				i = 0;
			}
			else {
				i++;
			}
		}
		x.addAll(hold);
		hold.clear();
	}
		
	private static void compareHigh(List<Integer> x, List<Integer> y) {
		int player1High = findHighCard(x);
		int player2High = findHighCard(y);
		if (player1High > player2High) {
			wins++;
			System.out.println("Player 1 wins");
		}
		else if (player1High == player2High) {
			compareNextHigh(x, y);
		}
		else {
			System.out.println("Player 2 wins");
		}
	}

	private static void compareNextHigh(List<Integer> x, List<Integer> y) {
		for(int i = x.size() - 2; i >= 0; i--) {
			if (x.get(i) > y.get(i)) {
				wins++;
				System.out.println("Player 1 wins");
				break;
			}
			else if (x.get(i) == y.get(i)) {
				continue;
			}
			else {
				System.out.println("Player 2 wins");
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
			wins++;
			System.out.println("Player 1 wins");
		}
		else if(buildValue1 == buildValue2) {
			compareHigh(x, y);
		}
		else {
			System.out.println("Player 2 wins");
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