package checkers;

import boardgame.Position;

public class CheckersPosition {

	/*Instancia��o dos atributos e do construtor, que tem uma
	 * programa��o defensiva, para verificar se a posi��o de damas,
	 * N�O de matriz, foi correta. Vale ressaltar, que os operadores de
	 * maior, menor... funcionam com letras atrav�s do codigo unicode.
	 */
	private char column;
	private int row;
	
	public CheckersPosition(char column, int row) {
		if(column < 'a' || column > 'h' || row < 1 || row > 8) {
			throw new CheckersException("Error instantiating Checkersposition. Valide values are from a1 to h8.");
		}
		this.column = column;
		this.row = row;
	}
	
	/*M�todos getters dos atributos.
	 */
	public char getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}

	/*M�todo respons�vel por transformar uma posi��o de damas, em uma 
	 * posi��o de matriz. Esse m�todo � protected com o intuito de todas 
	 * as classes deste pacote poderem utilizar este m�todo diretamente.
	 * A logica utilizada, foi uma subtra��o do codigo unicode da letra.
	 */
	protected Position toPosition() {
		return new Position(8 - row, column - 'a');  
	}

	/*M�todo respons�vel por transformar uma posi��o de matriz em uma 
	 * posi��o de damas. Tamb�m � protected, e � est�tico. ou seja, 
	 * n�o precisa de instancia��o, e por retonar o construtor da propria
	 * classe.
	 */
	protected static CheckersPosition fromPosition(Position position) {
		return new CheckersPosition((char)('a' + position.getColumn()), 8 + position.getRow());  
	}
	
	/*M�todo sobreposto da classe Object, com o intuito de mostrar a posi��o
	 * de uma pe�a de damas. Como a posi��o � junto, ex: A2, precisamos do
	 * abre aspas para for�ar uma concatena��o de strings.
	 */
	@Override
	public String toString() {
		return "" + column + row; 
		
	}
}
