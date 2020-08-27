package boardgame;


public abstract class Piece {  //peça no tabuleiro

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
	
	public abstract boolean[][] possibleCatchMoves();   //metodo que retorna a matriz de movimentos de cada peça
	
	public abstract boolean[][] possibleSimpleMoves();
	
	public boolean possibleCatchMove(Position position) {
		return possibleCatchMoves()[position.getRow()][position.getColumn()];
	}
	
	public boolean possibleSimpleMove(Position position) {
		return possibleSimpleMoves()[position.getRow()][position.getColumn()];
	}
	
	public boolean isThereAnyPossibleMoves() {
		boolean[][] matCatch = possibleCatchMoves();
		boolean[][] matSimple = possibleSimpleMoves();
		
		for(int i = 0; i < matCatch.length; i++) {
			for(int j = 0; j < matCatch[i].length; j++) {   //percorre a matriz
				if(matCatch[i][j] || matSimple[i][j]) {   //se tiver true
					return true;    //retorna que tem movimento possivel
				}
			}
		}
		return false;   //senao, retorna que nao tem movimento possivel	
	}
}
