package boardgame;

public class Position {  //posição no tabuleiro

	//attributes
	private int row;
	private int column;
	
	//constructors
	public Position(int row, int column) {
		this.row = row;
		this.column = column;
	}

	//methods
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}
	
	public void setValues(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	@Override  //sobreposição do metodo
	public String toString() {
		return row + "," + column;
	}
}
