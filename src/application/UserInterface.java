package application;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import checkers.CheckersMatch;
import checkers.CheckersPiece;
import checkers.CheckersPosition;
import checkers.Color;

public class UserInterface {

	/*Codigos das cores utilizadas no jogo. Os codigos foram pego no site:
	 * // https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
	 */
	public static final String ANSI_RESET = "\u001B[0m";  
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
	
	/*Método reponsável por limpar a tela do terminal, e o codigo foi
	 * pego no site: https://stackoverflow.com/questions/2979383/java-clear-the-console
	 */
	public static void clearScreen() {      
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}	
	
	/*Método estatico, pois não precisa de objeto, e responsável por ler
	 * os dados inseridos pelo usuario, como a posição de origem e de destino.
	 * Como a leitura é feita com um string, seprarou o string por meio do uso 
	 * do substring e transformou para inteiro. Se o método tiver alguma exceção
	 * essa exceção é tratada neste próprio método.
	 */
	public static CheckersPosition readCheckersPosition(Scanner sc) {
		try {
			String s = sc.nextLine();  
			char column = s.charAt(0); 
			int row = Integer.parseInt(s.substring(1));  
			return new CheckersPosition(column, row);   
		}
		catch(RuntimeException e) {
			throw new InputMismatchException("Error reading Checkersposition. Valid values are from a1 to h8.");
		}
	}
	
	/*Método estático responsável por imprimir a partida, imprimi-se o tabuleiro, as peças
	 * capturadas, o turno e o proximo jogador.
	 */
	public static void printMatch(CheckersMatch checkersMatch, List<CheckersPiece> captured) {
		printBoard(checkersMatch.getPieces());
		System.out.println();
		printCapturedPieces(captured);
		System.out.println("Turn: " + checkersMatch.getTurn());
		
		if(checkersMatch.getCurrentPlayer() == Color.WHITE) {
			System.out.println("Waiting player: " + ANSI_YELLOW + "WHITE" + ANSI_RESET);
		}
		else {
			System.out.println("Waiting player: BLACK");
		}
		
	}
	
	/*Método estático responsável por imprimir o tabuleiro, para isso ele recebe a matriz de 
	 * peças, imprimir os numeros das casas, letras de cada casa, cor de cada casa.
	 */
	public static void printBoard(CheckersPiece[][] checkersPiece) {   
		for(int i = 0; i < checkersPiece.length; i++) {
			System.out.print(ANSI_WHITE + (8 - i) + " " + ANSI_RESET);
			for(int j = 0; j < checkersPiece[i].length; j++) {
				
				if((i % 2 == 0 || i == 0) && (j == 0 || j % 2 == 0)) {  
					System.out.print(ANSI_WHITE_BACKGROUND);
				}
				else if(i % 2 != 0 && j % 2 != 0) {
					System.out.print(ANSI_WHITE_BACKGROUND);
				}
				else {
					System.out.print(ANSI_GREEN_BACKGROUND);
				}
				
				printPiece(checkersPiece[i][j], false);
			}
			System.out.println();
		}
		System.out.println(ANSI_WHITE + "   a  b  c  d  e  f  g  h" + ANSI_RESET);
	}
	
	/*Método estático, sobrecarga do anterior, responsável por imprimir o tabuleiro, para isso 
	 * ele recebe a matriz de peças e a matriz booleana de movimentos possiveis da peça da posição
	 * de origem. Assim,imprimirá o tabuleiro, e com a ajuda do método printPiece, ele imprime as 
	 * peças no tabuleiro, bem como imprimirá em azul os movimentos possiveis da peça da posiçao 
	 * selecionada.
	 */
	public static void printBoard(CheckersPiece[][] checkersPiece, boolean[][] possibleMoves) {    
		for(int i = 0; i < checkersPiece.length; i++) {
			System.out.print(ANSI_RED + (8 - i) + " " + ANSI_RESET);
			for(int j = 0; j < checkersPiece[i].length; j++) {
				
				if((i % 2 == 0 || i == 0) && (j == 0 || j % 2 == 0)) {   
					System.out.print(ANSI_WHITE_BACKGROUND);
				}
				else if(i % 2 != 0 && j % 2 != 0) {
					System.out.print(ANSI_WHITE_BACKGROUND);
				}
				else {
					System.out.print(ANSI_GREEN_BACKGROUND);
				}
				
				printPiece(checkersPiece[i][j], possibleMoves[i][j]);   
			}
			System.out.println();
		}
		System.out.println(ANSI_RED + "   a  b  c  d  e  f  g  h" + ANSI_RESET);
	}
	
	/*Método estático responsável por imprimir as peças do tabuleiro e imprimir em 
	 * azul os movimentos possiveis de cada peça.
	 */
	private static void printPiece(CheckersPiece checkersPiece, boolean background) {
		if(background) {
			System.out.print(ANSI_BLUE_BACKGROUND);
		}
		
		if(checkersPiece == null) {
			System.out.print("   "+ ANSI_RESET );
		}
		else if(checkersPiece.getColor() == Color.WHITE) {
			System.out.print(ANSI_YELLOW + " "+checkersPiece.toString() +" " + ANSI_RESET);  
		}
		else {
			System.out.print(ANSI_BLACK + " "+checkersPiece.toString() +" " + ANSI_RESET);  
		}
	}
	
	/*Método estático responsável por receber a lista de peças capturadas, criar uma lista
	 * contendo as peças da cor branca, e outra lista contendo as peças da cor preta. Em 
	 * seguida é impresso a quantidade de peças em cada lista. 
	 */
	private static void printCapturedPieces(List<CheckersPiece> captured) {
		List<CheckersPiece> white = captured.stream().filter(x -> x.getColor() == Color.WHITE).collect(Collectors.toList());
		List<CheckersPiece> black = captured.stream().filter(x -> x.getColor() == Color.BLACK).collect(Collectors.toList());
		System.out.println(ANSI_RED + "Captured pieces: " + ANSI_RESET);
		System.out.println(ANSI_YELLOW);
		System.out.print("White: ");
		System.out.println("[" + white.size() + "]");
		System.out.print(ANSI_RESET);
		System.out.print("Black: ");
		System.out.println("[" + black.size() + "]");
		System.out.println();
	}
}
