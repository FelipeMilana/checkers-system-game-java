package boardgame;


public abstract class Piece {  //pe�a no tabuleiro

	/*Instancia��o das associa��oes e do construtor. A variavel
	 * position � protected pois tem o intuito de todas as classes do
	 * mesmo pacote e as subclasses de Piece terem acesso. O seu valor 
	 * inicial ser� nulo, pois a posi��o de uma pe�a nao alocada ainda
	 * tem que ser nula.
	 */
	protected Position position;  
	private Board board;          
	
	public Piece(Board board) {  
		this.board = board;
	}
	
	/*M�todo getter
	 */
	protected Board getBoard() {   
		return board;
	}
	
	/*M�todo respons�vel por retornar a matriz de possiveis movimentos
	 * para uma pe�a capturar uma pe�a advers�ria. Ela ser� uma matriz 
	 * booleana, com true na posi��o em que o movimento � possivel. Esse
	 * m�todo � abstrato pois ele � mto gen�rico nesta classe, sendo 
	 * poss�vel implementa-lo apenas na classe da pe�a em si. Assim, por
	 * possuir um m�todo abstrato, essa classe tamb�m se torna abstrata.
	 */
	public abstract boolean[][] possibleCatchMoves();   
	
	/*Este m�todo temo mesmo papel do m�todo anterior, por�m ele retorna
	 * a matriz com os poss�veis movimentos da pe�a apenas para ela andar.
	 */
	public abstract boolean[][] possibleSimpleMoves();
	
	/*Este m�todo recebe uma posi��o e retorna se naquela posi��o na matriz
	 * de poss�veis movimentos de captura de pe�a advers�ria, existe um movimento
	 * poss�vel ou n�o. Al�m disso, este m�todo concreto utiliza um m�todo abstrato,
	 * que so ser� implementado nas subclasses, isso se chama Template Method.
	 */
	public boolean possibleCatchMove(Position position) {
		return possibleCatchMoves()[position.getRow()][position.getColumn()];
	}
	
	/*Este m�todo recebe uma posi��o e retorna se naquela posi��o na matriz
	 * de poss�veis movimentos de andar, existe um movimento poss�vel ou n�o.
	 */
	public boolean possibleSimpleMove(Position position) {
		return possibleSimpleMoves()[position.getRow()][position.getColumn()];
	}
	
	/*M�todo respons�vel por verificar se existe ou n�o um movimento poss�vel
	 * para aquela pe�a, seja ela de captura ou de apenas andar.
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
