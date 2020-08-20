package boardgame;

public class Piece {  //peça no tabuleiro

	//association
	protected Position position;  //é apenas uma posição de matriz(inicialmente nula) e tera acesso a esse atributo, as subclasses dessa classe
	private Board board;          //inicialmente nulo
	
	//constructors
	public Piece(Board board) {  
		this.board = board;
	}
	
	//methods
	protected Board getBoard() {   
		return board;
	}
}
