package checkers;

import boardgame.Position;

public class CheckersPosition {

	//attributes
	private char column;
	private int row;
	
	//constructors
	public CheckersPosition(char column, int row) {
		if(column < 'a' || column > 'h' || row < 1 || row > 8) {
			throw new CheckersException("Error instantiating Checkersposition. Valide values are from a1 to h8.");
		}
		this.column = column;
		this.row = row;
	}
	
	//methods
	public char getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}

	protected Position toPosition() {
		return new Position(8 - row, column - 'a');   //subtração do codigo unicode
	}

	protected static CheckersPosition fromPosition(Position position) {
		return new CheckersPosition((char)('a' + position.getColumn()), 8 + position.getRow());   //esta nessa classe por isso é estatico
	}
	
	@Override
	public String toString() {
		return "" + column + row; //usa para forçar uma concatenaçãode strings
	}
}
