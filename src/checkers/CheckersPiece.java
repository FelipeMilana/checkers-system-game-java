package checkers;

import boardgame.Board;
import boardgame.Piece;

public class CheckersPiece extends Piece {   //tambem é abstrata
	
	//association
	private Color color;
	
	//constructors
	public CheckersPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}
	
	//methods
	public Color getColor() {
		return color;
	}
}
