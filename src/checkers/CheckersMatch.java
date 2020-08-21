package checkers;

import boardgame.Board;

public class CheckersMatch {

	//associations
	private Board board;
	
	//constructors
	public CheckersMatch() {
		board = new Board(8, 8);    //instanciamos um tabuleiro nulo 8x8
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
}
