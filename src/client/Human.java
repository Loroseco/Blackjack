package client;

import java.util.Scanner;

public class Human extends Player {
	
	@Override
	public String getMove(Object scanObj) {
		Scanner scan = (Scanner) scanObj;
		System.out.print("SELECT MOVE (H = HIT, S = STAY, F = FOLD): ");
		return scan.next();
	}
}
