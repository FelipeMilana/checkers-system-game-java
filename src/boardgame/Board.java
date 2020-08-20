package boardgame;

public class Board {

	//attributes
	private int rows;  
	private int columns;
	
	//associations
	private Piece[][] pieces;  //cria uma variavel do tipo matriz de peças
	
	//constructors
	public Board(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];   //instancia na variavel pieces uma matriz nula com linha e colunas informadas no construtor   
	}

	//methods
	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}	
}
