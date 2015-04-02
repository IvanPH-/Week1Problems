package week1Problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class P6Player3 extends P6 {

	public static List<String> cardConstructor(List<String> x, List<String> y) {
		List<String> deck = removeFromDeck(x, y);
		List<String> hand = new ArrayList<>();
		
		deckToHand(deck, hand);
		return hand;
	}
	

	private static List<String> deckToHand(List<String> deck, List<String> hand) {
		Collections.shuffle(deck);
		for(int i = 0; i <= 4; i++) {
			hand.add(deck.get(0));
			deck.remove(0);
		}
		return hand;
		
	}

	private static List<String> removeFromDeck(List<String> x, List<String> y) {
		List<String> z = new ArrayList<>();
		z.add("2H");
		z.add("2S");
		z.add("2C");
		z.add("2D");
		z.add("3H");
		z.add("3S");
		z.add("3C");
		z.add("3D");
		z.add("4H");
		z.add("4S");
		z.add("4C");
		z.add("4D");
		z.add("5H");
		z.add("5S");
		z.add("5C");
		z.add("5D");
		z.add("6H");
		z.add("6S");
		z.add("6C");
		z.add("6D");
		z.add("7H");
		z.add("7S");
		z.add("7C");
		z.add("7D");
		z.add("8H");
		z.add("8S");
		z.add("8C");
		z.add("8D");
		z.add("9H");
		z.add("9S");
		z.add("9C");
		z.add("9D");
		z.add("TH");
		z.add("TS");
		z.add("TC");
		z.add("TD");
		z.add("JH");
		z.add("JS");
		z.add("JC");
		z.add("JD");
		z.add("QH");
		z.add("QS");
		z.add("QC");
		z.add("QD");
		z.add("KH");
		z.add("KS");
		z.add("KC");
		z.add("KD");
		z.add("AH");
		z.add("AS");
		z.add("AC");
		z.add("AD");
		for(int i = 0; i <= x.size() - 1; i++) {
			for(int j = 0; j <= z.size() - 1; j++) {
				if(z.get(j).equals(x.get(i)) || z.get(j).equals(y.get(i))) {
					z.remove(j);
					j -= 1;
				}
			}
		}
		return z;
	}
	
	public static void runPoker(List<Character> x, List<Character> y, List<Character> z) {
		boolean[] player1Boolean = new boolean[8];
		boolean[] player2Boolean = new boolean[8];
		boolean[] player3Boolean = new boolean[8];
		List<Integer> a = cleanHands(player1Boolean, x);
		List<Integer> b = cleanHands(player2Boolean, y);
		List<Integer> c = cleanHands(player3Boolean, z);
		Collections.sort(a);
		Collections.sort(b);
		Collections.sort(c);
		compare(a, b, c, player1Boolean, player2Boolean, player3Boolean);
	}


	private static void compare(List<Integer> x, List<Integer> y, List<Integer> z, boolean[] a, boolean[] b, boolean[] c) {
		checkOfAKindSeries(x, a);
		checkOfAKindSeries(y, b);
		checkOfAKindSeries(z, c);
		//If statment in a method to pass both a, b to make sure fullhouse(which we checked for), and four of a kind aren't possibilities. If they aren't we run to check for pairs
		checkToCheckPair(x, a);
		checkToCheckPair(y, b);
		checkToCheckPair(z, c);
		//If statement to check for straights if NONE of the values aside from flush which is an exception is false. If false checks for straight
		checkToCheckStraight(x, a);
		checkToCheckStraight(y, b);
		checkToCheckStraight(z, c);
		compareHands(x, y, z, a, b, c);
	}


	private static void compareHands(List<Integer> x, List<Integer> y, List<Integer> z, boolean[] a, boolean[] b, boolean[] c) 
	{
		int buildValue1 = findValue(a);
		int buildValue2 = findValue(b);
		int buildValue3 = findValue(c);
		List<Integer> player1 = x;
		List<Integer> player2 = y;
		
			if(buildValue1 > buildValue2 && buildValue1 > buildValue3) {
				wins++;
				System.out.println("Player 1 wins");
			}
			else if(buildValue1 == buildValue2 && buildValue1 > buildValue3) {
				//This is whats happening. Override the compareHigh method and accept three players at once to compare with
				compareHigh(x, y);
			}
			else if(buildValue1 == buildValue3 && buildValue1 > buildValue2) {
				compareHigh(x, z);
			}
			else if(buildValue1 == buildValue2 && buildValue1 == buildValue3) {
				compareHigh(x, y, z);
			}
			else {
				System.out.println("Another player won");
			}
	}
	
	protected static void compareHigh(List<Integer> x, List<Integer> y, List<Integer> z) {
		int player1High = findHighCard(x);
		int player2High = findHighCard(y);
		int player3High = findHighCard(z);
		if (player1High > player2High && player1High > player3High) {
			wins++;
			System.out.println("Player 1 wins");
		}
		else if (player1High == player2High && player1High > player3High) {
			compareNextHigh(x, y);
		}
		else if (player1High == player3High && player1High > player2High) {
			compareNextHigh(x, z);
		}
		else if (player1High == player2High && player1High == player3High) {
			compareNextHigh(x, y, z);
		}
		else {
			System.out.println("Another Player won");
		}
	}

	protected static void compareNextHigh(List<Integer> x, List<Integer> y, List<Integer> z) {
		boolean checks = false;
		for(int i = x.size() - 2; i >= 0; i--) {
			if (x.get(i) > y.get(i)) {
				checks = true;
				break;
			}
			else if (x.get(i) == y.get(i)) {
				continue;
			}
			else {
				break;
			}
		}
		
		if(checks == true) {
			for(int j = x.size() - 2; j >= 0; j--) {
				if (x.get(j) > z.get(j)) {
					wins++;
					System.out.println("Player 1 won");
					break;
				}
				else if (x.get(j) == y.get(j)) {
					continue;
				}
				else {
					System.out.println("Another player won");
					break;
				}
			}
		}
		
	}
}
	
