package boardgame;

public class Board {

	/*Instanciação dos atributos, das associações, no caso uma matriz
	 * do tipo Piece e do construtor. No construtor é feito uma progra
	 * mação defensiva, se caso o tabuleiro for iniciado com o tamanho
	 * inválido. Em seguida, a matriz de peças do tipo Piece é instanciada
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

	/*Métodos getters dos atributos.
	 */
	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}
	
	/*Método responsável por receber uma linha e coluna e retornar
	 * a peça correspondente na matriz de peças
	 */
	public Piece piece(int row, int column) { 
		if(!positionExists(row, column)) {
			throw new BoardException("Position not on the board!");  
		}
		return pieces[row][column];
	}

	/*Método responsável por receber uma posição e retornar a peça
	 * correspondente na matriz de peças. Este método e o método 
	 * acima são um exemplo de sobrecarga, possuem um mesmo nome,
	 * mas parâmetros diferentes.
	 */
	public Piece piece(Position position) {  
		if(!positionExists(position)) {
			throw new BoardException("Position not on the board!");
		}
		return pieces[position.getRow()][position.getColumn()];
	}
	
	/*Método responsável por alocar uma peça no tabuleiro, para
	 * isso ele recebe uma peça e uma posição, coloca a peça na 
	 * matriz na posição inserida, e troca a posição da propria
	 * peça de nulo para a posição da matriz.
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
	
	/*Método responsável por remover uma peça do tabuleiro, para
	 * isso ele recebe uma posição, aloca a peça contida nessa posição
	 * em uma peça auxiliar, troca a posição da peça para nulo, e em 
	 * seguida, coloca nulo na matriz na posição escolhida.
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
	
	/*Métodos responsáveis pela programação defensiva, verificando se 
	 * a posição existe, e se existe alguma peça na posição escolhida.
	 * Se não houver, lançam exceções.
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
