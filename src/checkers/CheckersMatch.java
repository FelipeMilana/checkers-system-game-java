package checkers;

import boardgame.Board;
import boardgame.Position;
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
	
	private void initialSetup() {
		board.placePiece(new Rock(board, Color.WHITE), new Position(0, 1));
		board.placePiece(new Rock(board, Color.WHITE), new Position(0, 3));
		board.placePiece(new Rock(board, Color.WHITE), new Position(0, 5));
		board.placePiece(new Rock(board, Color.WHITE), new Position(0, 7));
		board.placePiece(new Rock(board, Color.WHITE), new Position(1, 0));
		board.placePiece(new Rock(board, Color.WHITE), new Position(1, 2));
		board.placePiece(new Rock(board, Color.WHITE), new Position(1, 4));
		board.placePiece(new Rock(board, Color.WHITE), new Position(1, 6));
		board.placePiece(new Rock(board, Color.WHITE), new Position(2, 1));
		board.placePiece(new Rock(board, Color.WHITE), new Position(2, 3));
		board.placePiece(new Rock(board, Color.WHITE), new Position(2, 5));
		board.placePiece(new Rock(board, Color.WHITE), new Position(2, 7));
		
		board.placePiece(new Rock(board, Color.BLACK), new Position(5, 0));
		board.placePiece(new Rock(board, Color.BLACK), new Position(5, 2));
		board.placePiece(new Rock(board, Color.BLACK), new Position(5, 4));
		board.placePiece(new Rock(board, Color.BLACK), new Position(5, 6));
		board.placePiece(new Rock(board, Color.BLACK), new Position(6, 1));
		board.placePiece(new Rock(board, Color.BLACK), new Position(6, 3));
		board.placePiece(new Rock(board, Color.BLACK), new Position(6, 5));
		board.placePiece(new Rock(board, Color.BLACK), new Position(6, 7));
		board.placePiece(new Rock(board, Color.BLACK), new Position(7, 0));
		board.placePiece(new Rock(board, Color.BLACK), new Position(7, 2));
		board.placePiece(new Rock(board, Color.BLACK), new Position(7, 4));
		board.placePiece(new Rock(board, Color.BLACK), new Position(7, 6));
	}
}
