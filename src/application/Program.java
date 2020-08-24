package application;

import checkers.CheckersMatch;

public class Program {

	public static void main(String[] args) {
		
		CheckersMatch checkersMatch = new CheckersMatch();   //instanciamos uma partida de dama
		UserInterface.printBoard(checkersMatch.getPieces());
	}
}
