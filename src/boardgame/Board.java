package boardgame;

public class Board {

	//attributes
	private int rows;  
	private int columns;
	
	//associations
	private Piece[][] pieces;  //cria uma variavel do tipo matriz de pe�as
	
	//constructors
	public Board(int rows, int columns) {
		if(rows < 1 || columns < 1) {
			throw new BoardException("Error creating the board: needs 1 row and 1 column at least!");
		}
		
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];   //instancia na variavel pieces uma matriz nula com linha e colunas informadas no construtor   
	}

	//methods
	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}
	
	public Piece piece(int row, int column) {  //retorna a pe�a informando linha e coluna
		if(!positionExists(row, column)) {
			throw new BoardException("Position not on the board!");  //nao estamos tratando, apenas lan�ando
		}
		return pieces[row][column];
	}

	public Piece piece(Position position) {   //retorna a pe�a informando a posi��o
		if(!positionExists(position)) {
			throw new BoardException("Position not on the board!");
		}
		return pieces[position.getRow()][position.getColumn()];
	}
	
	public void placePiece(Piece piece, Position position) {
		if(!positionExists(position)) {
			throw new BoardException("Position not on the board!");
		}
		
		if(thereIsAPiece(position)) {
			throw new BoardException("There is already piece in this position" + position);
		}
		
		pieces[position.getRow()][position.getColumn()] = piece;  //adiciona a pe�a nessa posi��o da matriz
		piece.position = position;  //posi��o deixa de ser nula;
	}
	
	public Piece removePiece(Position position) {
		if(!positionExists(position)) {
			throw new BoardException("Position not on the board!");
		}
		
		if(piece(position) == null) {  //se ja for nulo 
			return null;
		}
		
		Piece p = piece(position);  //adiciona na variavel p, o que estiver na posi��o informada
		p.position = null;    //a posi��o da pe�a se torna nula
		pieces[position.getRow()][position.getColumn()] = null;  //a posi��o ma matriz se torna nula
		return p;		
	}
	
	public boolean positionExists(Position position) {    //exce��es
		return positionExists(position.getRow(), position.getColumn());
	}
	
	public boolean thereIsAPiece(Position position) {
		if(!positionExists(position)) {
			throw new BoardException("Position not on the board!");
		}
		return pieces[position.getRow()][position.getColumn()] != null;
	}
	
	private boolean positionExists(int row, int column) {   //de 0 a 7
		return row >= 0 && row < rows && column >= 0 && column < columns;   //rows = columns = 8
	}
}
