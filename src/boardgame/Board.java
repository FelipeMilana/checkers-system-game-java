package boardgame;

public class Board {

	/*Instancia��o dos atributos, das associa��es, no caso uma matriz
	 * do tipo Piece e do construtor. No construtor � feito uma progra
	 * ma��o defensiva, se caso o tabuleiro for iniciado com o tamanho
	 * inv�lido. Em seguida, a matriz de pe�as do tipo Piece � instanciada
	 * com os valores iniciais nulos.
	 */
	private int rows;  
	private int columns;
	
	private Piece[][] pieces;  
	
	public Board(int rows, int columns) {
		if(rows < 1 || columns < 1) {
			throw new BoardException("Error creating the board: needs 1 row and 1 column at least!");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];   
	}

	/*M�todos getters dos atributos.
	 */
	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}
	
	/*M�todo respons�vel por receber uma linha e coluna e retornar
	 * a pe�a correspondente na matriz de pe�as
	 */
	public Piece piece(int row, int column) { 
		if(!positionExists(row, column)) {
			throw new BoardException("Position not on the board!");  
		}
		return pieces[row][column];
	}

	/*M�todo respons�vel por receber uma posi��o e retornar a pe�a
	 * correspondente na matriz de pe�as. Este m�todo e o m�todo 
	 * acima s�o um exemplo de sobrecarga, possuem um mesmo nome,
	 * mas par�metros diferentes.
	 */
	public Piece piece(Position position) {  
		if(!positionExists(position)) {
			throw new BoardException("Position not on the board!");
		}
		return pieces[position.getRow()][position.getColumn()];
	}
	
	/*M�todo respons�vel por alocar uma pe�a no tabuleiro, para
	 * isso ele recebe uma pe�a e uma posi��o, coloca a pe�a na 
	 * matriz na posi��o inserida, e troca a posi��o da propria
	 * pe�a de nulo para a posi��o da matriz.
	 */
	public void placePiece(Piece piece, Position position) {
		if(!positionExists(position)) {
			throw new BoardException("Position not on the board!");
		}
		
		if(thereIsAPiece(position)) {
			throw new BoardException("There is already piece in this position" + position);
		}
		
		pieces[position.getRow()][position.getColumn()] = piece;  
		piece.position = position;  
	}
	
	/*M�todo respons�vel por remover uma pe�a do tabuleiro, para
	 * isso ele recebe uma posi��o, aloca a pe�a contida nessa posi��o
	 * em uma pe�a auxiliar, troca a posi��o da pe�a para nulo, e em 
	 * seguida, coloca nulo na matriz na posi��o escolhida.
	 */
	public Piece removePiece(Position position) {
		if(!positionExists(position)) {
			throw new BoardException("Position not on the board!");
		}
		if(piece(position) == null) {   
			return null;
		}
		
		Piece p = piece(position);  
		p.position = null;    
		pieces[position.getRow()][position.getColumn()] = null;  
		return p;		
	}
	
	/*M�todos respons�veis pela programa��o defensiva, verificando se 
	 * a posi��o existe, e se existe alguma pe�a na posi��o escolhida.
	 * Se n�o houver, lan�am exce��es.
	 */
	public boolean positionExists(Position position) {    
		return positionExists(position.getRow(), position.getColumn());
	}
	
	public boolean thereIsAPiece(Position position) {
		if(!positionExists(position)) {
			throw new BoardException("Position not on the board!");
		}
		return pieces[position.getRow()][position.getColumn()] != null;
	}
	
	private boolean positionExists(int row, int column) {  
		return row >= 0 && row < rows && column >= 0 && column < columns;   
	}
}
