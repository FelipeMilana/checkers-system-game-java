package application;

import checkers.CheckersPiece;
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
	
	//impressao do tabuleiro
	public static void printBoard(CheckersPiece[][] checkersPiece) {   
		for(int i = 0; i < checkersPiece.length; i++) {
			System.out.print(ANSI_RED + (8 - i) + " " + ANSI_RESET);
			for(int j = 0; j < checkersPiece[i].length; j++) {
				printPiece(checkersPiece[i][j]);
			}
			System.out.println();
		}
		System.out.println(ANSI_RED + "  a b c d e f g h" + ANSI_RESET);
	}
	
	//impressao de uma pe�a
	private static void printPiece(CheckersPiece checkersPiece) {
		if(checkersPiece == null) {
			System.out.print(ANSI_PURPLE + "-" + ANSI_RESET);
		}
		
		else if(checkersPiece.getColor() == Color.WHITE) {
			System.out.print(ANSI_WHITE + checkersPiece.toString() + ANSI_RESET);  //imprimo o que tiver no toString da pe�a branca
		}
		
		else {
			System.out.print(ANSI_YELLOW + checkersPiece.toString() + ANSI_RESET); //imprimo o que tiver no toString da pe�a amarela
		}
		System.out.print(" ");
	}
}
