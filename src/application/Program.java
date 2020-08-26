package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import javax.jws.soap.SOAPBinding.Use;

import checkers.CheckersException;
import checkers.CheckersMatch;
import checkers.CheckersPiece;
import checkers.CheckersPosition;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in); 
		CheckersMatch checkersMatch = new CheckersMatch();   //instanciamos uma partida de dama
		
		while(true) {
			try {
				System.out.println();
				UserInterface.clearScreen();
				UserInterface.printMatch(checkersMatch);
				System.out.println();
				System.out.print("Source: ");
				CheckersPosition source = UserInterface.readCheckersPosition(sc);
				
				boolean[][] possibleMoves = checkersMatch.possibleMoves(source); 
				UserInterface.clearScreen();  
				UserInterface.printBoard(checkersMatch.getPieces(), possibleMoves);
				
				System.out.println();
				System.out.print("Target: ");
				CheckersPosition target = UserInterface.readCheckersPosition(sc);
				
				CheckersPiece capturedPiece = checkersMatch.checkersMove(source, target);
				
				/*while(capturedPiece != null) {
					CheckersPosition newSource = target;
					System.out.println(newSource.toString());
					possibleMoves = checkersMatch.possibleMoves(newSource); 
					UserInterface.clearScreen();  
					UserInterface.printBoard(checkersMatch.getPieces(), possibleMoves);
					
					System.out.println();
					System.out.print("Target: ");
					CheckersPosition newTarget = UserInterface.readCheckersPosition(sc);
					
					capturedPiece = checkersMatch.checkersMove(newSource, newTarget);
					newTarget = target;
				} */
				
				
			}
			
			catch(CheckersException e) {
				System.out.print(e.getMessage());
				sc.nextLine();
			}
			
			catch(InputMismatchException e) {
				System.out.print(e.getMessage());
				sc.nextLine();
			}
		} 
	}
}
