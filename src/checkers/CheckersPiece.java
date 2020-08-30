package checkers;

import boardgame.Board;

import boardgame.Piece;
import boardgame.Position;

public abstract class CheckersPiece extends Piece {   //tambem é abstrata
	
	/*Instanciação da associação com a classe enumerada color,
	 * e do construtor. Essa classe é uma subclasse da classe 
	 * Piece, logo no construtor teremos que ter o super. Além 
	 * disso, por a classe Piece ser abstrata, e essa classe também
	 * ser genérica para a implementação dos métodos de movimentos, 
	 * essa classe também será abstrata.
	 */
	private Color color;
	
	public CheckersPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}
	
	/*Método getter da associação.
	 */
	public Color getColor() {
		return color;
	}
	
	/*Método responsável por retornar a posição de uma peça de damas.
	 */
	public CheckersPosition getCheckersPosition() {
		return CheckersPosition.fromPosition(position);
	}
	
	/*Método que recebe como parâmetro uma posição e retorna se a peça
	 * naquela posição é uma peça adversária, ou seja, não é nula e possui
	 * cor diferente.
	 */
	protected boolean isThereOpponentPiece(Position position) {
		CheckersPiece p = (CheckersPiece)getBoard().piece(position);
		return p != null && p.getColor() != color;      
	}
}
