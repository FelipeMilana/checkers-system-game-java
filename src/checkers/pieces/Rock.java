package checkers.pieces;

import boardgame.Board;
import checkers.CheckersPiece;
import checkers.Color;

public class Rock extends CheckersPiece{

	//constructors
	public Rock(Board board, Color color) {  
		super(board, color);
	}
	
	//method
	@Override
	public String toString() {
		return "O";
	}

}
