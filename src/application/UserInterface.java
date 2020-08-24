package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import checkers.CheckersPiece;
import checkers.CheckersPosition;
import checkers.Color;

public class UserInterface {

	//codigo de cores
	public static final String ANSI_RESET = "\u001B[0m";  // https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	
	//limpa a tela do terminal
	public static void clearScreen() {      // https://stackoverflow.com/questions/2979383/java-clear-the-console
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}	
	
	//ler os dados do usuario
	public static CheckersPosition readCheckersPosition(Scanner sc) {
		try {
			String s = sc.nextLine();  //ex: a2
			char column = s.charAt(0); //recebe a primeira letra da string, ex: a
			int row = Integer.parseInt(s.substring(1));  //transforma de string para int
			return new CheckersPosition(column, row);   //instanscia uma posição de damas
		}
		catch(RuntimeException e) {
			throw new InputMismatchException("Error reading Checkersposition. Valid values are from a1 to h8.");
		}
	}
	
	//impressao do tabuleiro
	public static void printBoard(CheckersPiece[][] checkersPiece) {   
		for(int i = 0; i < checkersPiece.length; i++) {
			System.out.print(ANSI_RED + (8 - i) + " " + ANSI_RESET);
			for(int j = 0; j < checkersPiece[i].length; j++) {
				
				if((i % 2 == 0 || i == 0) && (j == 0 || j % 2 == 0)) {   //montar o tabuleiro com as casas verdes e brancas
					System.out.print(ANSI_WHITE_BACKGROUND);
				}
				else if(i % 2 != 0 && j % 2 != 0) {
					System.out.print(ANSI_WHITE_BACKGROUND);
				}
				else {
					System.out.print(ANSI_GREEN_BACKGROUND);
				}
				
				printPiece(checkersPiece[i][j]);
			}
			
			System.out.println();
		}
		
		System.out.println(ANSI_RED + "   a  b  c  d  e  f  g  h" + ANSI_RESET);
	}
	
	//impressao de uma peça
	private static void printPiece(CheckersPiece checkersPiece) {
		
		if(checkersPiece == null) {
			System.out.print(ANSI_BLACK+ "   "+ ANSI_RESET );
		}
		
		else if(checkersPiece.getColor() == Color.WHITE) {
			System.out.print(ANSI_YELLOW + " "+checkersPiece.toString() +" " + ANSI_RESET);  //imprimo o que tiver no toString da peça branca
		}
		
		else {
			System.out.print(ANSI_BLACK + " "+checkersPiece.toString() +" " + ANSI_RESET);  //imprimo o que tiver no toString da peça branca
		}
	}
}
