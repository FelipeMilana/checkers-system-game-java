package checkers;

import boardgame.Board;
import checkers.pieces.Rock;

public class CheckersMatch {

	//associations
	private Board board;
	
	//constructors
	public CheckersMatch() {
		board = new Board(8, 8);    //instanciamos um tabuleiro nulo 8x8
		initialSetup();   //inicializamos as peças
	}
	
	//methods	
	public CheckersPiece[][] getPieces() {
		CheckersPiece[][] mat = new CheckersPiece[board.getRows()][board.getColumns()];  //cria uma matriz nula do mesmo tamanho do tabuleiro
		for(int i = 0; i < mat.length; i++) {
			for(int j = 0; j < mat[i].length; j++) {
				mat[i][j] = (CheckersPiece)board.piece(i, j);  //transforma a matriz nula do tipo piece em tipo CheckersPiece
			}
		}
		return mat;
	}
	
	private void placeNewPiece(char column, int row, CheckersPiece checkersPiece) {
		board.placePiece(checkersPiece, new CheckersPosition(column, row).toPosition());
	}
	
	private void initialSetup() {
		placeNewPiece('b', 8, new Rock(board, Color.WHITE));
		placeNewPiece('d', 8, new Rock(board, Color.WHITE));
		placeNewPiece('f', 8, new Rock(board, Color.WHITE));
		placeNewPiece('h', 8, new Rock(board, Color.WHITE));
		placeNewPiece('a', 7, new Rock(board, Color.WHITE));
		placeNewPiece('c', 7, new Rock(board, Color.WHITE));
		placeNewPiece('e', 7, new Rock(board, Color.WHITE));
		placeNewPiece('g', 7, new Rock(board, Color.WHITE));
		placeNewPiece('b', 6, new Rock(board, Color.WHITE));
		placeNewPiece('d', 6, new Rock(board, Color.WHITE));
		placeNewPiece('g', 6, new Rock(board, Color.WHITE));
		placeNewPiece('h', 6, new Rock(board, Color.WHITE));
		
		placeNewPiece('a', 3, new Rock(board, Color.BLACK));
		placeNewPiece('c', 3, new Rock(board, Color.BLACK));
		placeNewPiece('e', 3, new Rock(board, Color.BLACK));
		placeNewPiece('g', 3, new Rock(board, Color.BLACK));
		placeNewPiece('b', 2, new Rock(board, Color.BLACK));
		placeNewPiece('d', 2, new Rock(board, Color.BLACK));
		placeNewPiece('f', 2, new Rock(board, Color.BLACK));
		placeNewPiece('h', 2, new Rock(board, Color.BLACK));
		placeNewPiece('a', 1, new Rock(board, Color.BLACK));
		placeNewPiece('c', 1, new Rock(board, Color.BLACK));
		placeNewPiece('e', 1, new Rock(board, Color.BLACK));
		placeNewPiece('g', 1, new Rock(board, Color.BLACK));
	}
}
