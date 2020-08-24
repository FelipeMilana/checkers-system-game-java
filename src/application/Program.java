package application;

import java.util.Scanner;

import checkers.CheckersMatch;
import checkers.CheckersPiece;
import checkers.CheckersPosition;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in); 
		CheckersMatch checkersMatch = new CheckersMatch();   //instanciamos uma partida de dama
		
		while(true) {
			System.out.println();
			UserInterface.printBoard(checkersMatch.getPieces());
			System.out.println();
			System.out.print("Source: ");
			CheckersPosition source = UserInterface.readCheckersPosition(sc);
			
			System.out.println();
			System.out.print("Target: ");
			CheckersPosition target = UserInterface.readCheckersPosition(sc);
			
			CheckersPiece capturedPiece = checkersMatch.checkersMove(source, target);
		} 
		
		
		
	}
}
