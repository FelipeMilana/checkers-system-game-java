package boardgame;

public class Piece {  //peça no tabuleiro

	//association
	protected Position position;  //é apenas uma posição de matriz(inicialmente nula) e tera acesso a esse atributo, as subclasses dessa classe
	private Board board;          //inicialmente nulo
	
	//constructors
	public Piece(Board board) {  
		this.board = board;
	}
	
	//methods
	protected Board getBoard() {   
		return board;
	}
	
	/*public abstract boolean[][] possibleMoves();   //metodo que retorna a matriz de movimentos de cada peça
	
	public boolean possibleMove(Position position) {   //informa uma posição e verifica se naquela posição tem true or false na matriz de movimentos da peça
		return possibleMoves()[position.getRow()][position.getColumn()];
	}
	
	public boolean isThereAnyPossibleMove() {  //verifica se tem pelo menos 1 movimento possivel
		boolean[][] mat = possibleMoves();    //armazena na matriz mat os movimentos possiveis da peça
		for(int i = 0; i < mat.length; i++) {
			for(int j = 0; j < mat[i].length; j++) {   //percorre a matriz
				if(mat[i][j]) {   //se tiver true
					return true;    //retorna que tem movimento possivel
				}
			}
		}
		return false;   //senao, retorna que nao tem movimento possivel
	} */
}
