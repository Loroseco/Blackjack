package house;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Blackjack game = new Blackjack();
		Deck deck = new Deck(5);
		Scanner scan = new Scanner(System.in);
		while (true) {
			game.play(1, deck, scan);
			System.out.print("PLAY AGAIN? (Y/N): ");
			String input = scan.next();
			if (!input.toLowerCase().equals("y")) {
				break;
			}
		}
		scan.close();
	}
}
