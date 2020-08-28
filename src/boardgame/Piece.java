package boardgame;


public abstract class Piece {  //peça no tabuleiro

	/*Instanciação das associaçãoes e do construtor. A variavel
	 * position é protected pois tem o intuito de todas as classes do
	 * mesmo pacote e as subclasses de Piece terem acesso. O seu valor 
	 * inicial será nulo, pois a posição de uma peça nao alocada ainda
	 * tem que ser nula.
	 */
	protected Position position;  
	private Board board;          
	
	public Piece(Board board) {  
		this.board = board;
	}
	
	/*Método getter
	 */
	protected Board getBoard() {   
		return board;
	}
	
	/*Método responsável por retornar a matriz de possiveis movimentos
	 * para uma peça capturar uma peça adversária. Ela será uma matriz 
	 * booleana, com true na posição em que o movimento é possivel. Esse
	 * método é abstrato pois ele é mto genérico nesta classe, sendo 
	 * possível implementa-lo apenas na classe da peça em si. Assim, por
	 * possuir um método abstrato, essa classe também se torna abstrata.
	 */
	public abstract boolean[][] possibleCatchMoves();   
	
	/*Este método temo mesmo papel do método anterior, porém ele retorna
	 * a matriz com os possíveis movimentos da peça apenas para ela andar.
	 */
	public abstract boolean[][] possibleSimpleMoves();
	
	/*Este método recebe uma posição e retorna se naquela posição na matriz
	 * de possíveis movimentos de captura de peça adversária, existe um movimento
	 * possível ou não. Além disso, este método concreto utiliza um método abstrato,
	 * que so será implementado nas subclasses, isso se chama Template Method.
	 */
	public boolean possibleCatchMove(Position position) {
		return possibleCatchMoves()[position.getRow()][position.getColumn()];
	}
	
	/*Este método recebe uma posição e retorna se naquela posição na matriz
	 * de possíveis movimentos de andar, existe um movimento possível ou não.
	 */
	public boolean possibleSimpleMove(Position position) {
		return possibleSimpleMoves()[position.getRow()][position.getColumn()];
	}
	
	/*Método responsável por verificar se existe ou não um movimento possível
	 * para aquela peça, seja ela de captura ou de apenas andar.
	 */
	public boolean isThereAnyPossibleMoves() {
		boolean[][] matCatch = possibleCatchMoves();
		boolean[][] matSimple = possibleSimpleMoves();
		
		for(int i = 0; i < matCatch.length; i++) {
			for(int j = 0; j < matCatch[i].length; j++) {   
				if(matCatch[i][j] || matSimple[i][j]) {   
					return true;    
				}
			}
		}
		return false;   
	}
}
