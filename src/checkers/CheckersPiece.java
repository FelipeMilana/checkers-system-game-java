package checkers;

import boardgame.Board;

import boardgame.Piece;
import boardgame.Position;

public abstract class CheckersPiece extends Piece {   //tambem é abstrata
	
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
	
	protected boolean isThereOpponentPiece(Position position) {
		CheckersPiece p = (CheckersPiece)getBoard().piece(position);
		return p != null && p.getColor() != color;      //se houver uma peça e tiver cor diferente da peça 
	}
}
