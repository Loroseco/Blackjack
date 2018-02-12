package house;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Blackjack game = new Blackjack();
		Scanner scan = new Scanner(System.in);
		game.play(1, 5, scan);
		scan.close();
	}
}
