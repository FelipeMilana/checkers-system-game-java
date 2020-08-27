package application;

import java.util.ArrayList;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


import checkers.CheckersException;
import checkers.CheckersMatch;
import checkers.CheckersPiece;
import checkers.CheckersPosition;
import checkers.Color;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in); 
		CheckersMatch checkersMatch = new CheckersMatch();   //instanciamos uma partida de dama
		List<CheckersPiece> captured = new ArrayList<CheckersPiece>();
		List<CheckersPiece> white = new ArrayList<CheckersPiece>();
		List<CheckersPiece> black = new ArrayList<CheckersPiece>();
		
		while((white.size() == 12) || (black.size() == 12)) { 
			try {
				System.out.println();
				UserInterface.clearScreen();
				UserInterface.printMatch(checkersMatch, captured);
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
				
				if(capturedPiece != null) {
					captured.add(capturedPiece);
					
					if(capturedPiece.getColor() == Color.WHITE) {
						white.add(capturedPiece);
					}
					else {
						black.add(capturedPiece);
					}
				}
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
