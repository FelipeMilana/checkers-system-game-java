package checkers;

import boardgame.Board;

import boardgame.Piece;
import boardgame.Position;

public abstract class CheckersPiece extends Piece {   //tambem � abstrata
	
	/*Instancia��o da associa��o com a classe enumerada color,
	 * e do construtor. Essa classe � uma subclasse da classe 
	 * Piece, logo no construtor teremos que ter o super. Al�m 
	 * disso, por a classe Piece ser abstrata, e essa classe tamb�m
	 * ser gen�rica para a implementa��o dos m�todos de movimentos, 
	 * essa classe tamb�m ser� abstrata.
	 */
	private Color color;
	
	public CheckersPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}
	
	/*M�todo getter da associa��o.
	 */
	public Color getColor() {
		return color;
	}
	
	/*M�todo respons�vel por retornar a posi��o de uma pe�a de damas.
	 */
	public CheckersPosition getCheckersPosition() {
		return CheckersPosition.fromPosition(position);
	}
	
	/*M�todo que recebe como par�metro uma posi��o e retorna se a pe�a
	 * naquela posi��o � uma pe�a advers�ria, ou seja, n�o � nula e possui
	 * cor diferente.
	 */
	protected boolean isThereOpponentPiece(Position position) {
		CheckersPiece p = (CheckersPiece)getBoard().piece(position);
		return p != null && p.getColor() != color;      
	}
}
