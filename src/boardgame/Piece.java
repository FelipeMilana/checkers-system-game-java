package boardgame;

public class Piece {  //pe�a no tabuleiro

	//association
	protected Position position;  //� apenas uma posi��o de matriz(inicialmente nula) e tera acesso a esse atributo, as subclasses dessa classe
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
