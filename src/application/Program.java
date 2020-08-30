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
		
		/* instancia��o dos objetos e das listas, de pe�as capturadas,
		 * de pe�as brancas e de pe�as pretas.
		 */
		Scanner sc = new Scanner(System.in); 
		CheckersMatch checkersMatch = new CheckersMatch();   
		List<CheckersPiece> captured = new ArrayList<CheckersPiece>();
		List<CheckersPiece> white = new ArrayList<CheckersPiece>();
		List<CheckersPiece> black = new ArrayList<CheckersPiece>();
		
		/* Come�o da partida, enquanto as listas n�o possuem 12 pe�as,
		 *o jogo continua rodando. Abaixo, criamos uma partida, l�-se
		 *posi��o de origem da pe�a e a posi��o futura, fazendo em seguida
		 *o movimento. Se no movimento houver uma pe�a capturada, verifica 
		 *sua cor e adiciona essa pe�a � lista de pe�as capturadas e na lista
		 *de pe�as da cor da pe�a. Em seguida, verificamos se � possivel capturar
		 *novamente uma pe�a. Ap�s todos os movimentos serem feitos, ocorre a troca
		 *de turno.*/
		
		while((white.size() != 12) && (black.size() != 12)) { 
			try {
				System.out.println();
				UserInterface.clearScreen();
				UserInterface.printMatch(checkersMatch, captured, white, black);
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
				
					while(checkersMatch.CanCatch(target)) {
						CheckersPosition newSource = target;
						
						possibleMoves = checkersMatch.possibleMoves(newSource); 
						UserInterface.clearScreen();  
						UserInterface.printBoard(checkersMatch.getPieces(), possibleMoves);
						
						System.out.println();
						System.out.print("Target: ");
						CheckersPosition newTarget = UserInterface.readCheckersPosition(sc);
						
						capturedPiece = checkersMatch.checkersMove(newSource, newTarget);
						
						if(capturedPiece != null) {
							captured.add(capturedPiece);
							
							if(capturedPiece.getColor() == Color.WHITE) {
								white.add(capturedPiece);
							}
							else {
								black.add(capturedPiece);
							}
						}
						target = newTarget;
					}
				}
				CheckersPiece promoted = checkersMatch.piecePromoted(target);
				checkersMatch.nextTurn();
			}
			
			/* se houver alguma exce��oo durante o bloco try, ela
			 *ser� capturada e tratada durante os catch's abaixo 
			 */
			
			catch(CheckersException e) {
				System.out.print(e.getMessage());
				sc.nextLine();
			}
			
			catch(InputMismatchException e) {
				System.out.print(e.getMessage());
				sc.nextLine();
			}
		}
		
		/*Para limpar a tela e mostrar a partida final e o ganhador.
		 */
		UserInterface.clearScreen();
		UserInterface.printMatch(checkersMatch, captured, white, black);
	}
}
