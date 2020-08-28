package checkers;

import boardgame.Position;

public class CheckersPosition {

	/*Instanciação dos atributos e do construtor, que tem uma
	 * programação defensiva, para verificar se a posição de damas,
	 * NÂO de matriz, foi correta. Vale ressaltar, que os operadores de
	 * maior, menor... funcionam com letras através do codigo unicode.
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
	
	/*Métodos getters dos atributos.
	 */
	public char getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}

	/*Método responsável por transformar uma posição de damas, em uma 
	 * posição de matriz. Esse método é protected com o intuito de todas 
	 * as classes deste pacote poderem utilizar este método diretamente.
	 * A logica utilizada, foi uma subtração do codigo unicode da letra.
	 */
	protected Position toPosition() {
		return new Position(8 - row, column - 'a');  
	}

	/*Método responsável por transformar uma posição de matriz em uma 
	 * posição de damas. Também é protected, e é estático. ou seja, 
	 * não precisa de instanciação, e por retonar o construtor da propria
	 * classe.
	 */
	protected static CheckersPosition fromPosition(Position position) {
		return new CheckersPosition((char)('a' + position.getColumn()), 8 + position.getRow());  
	}
	
	/*Método sobreposto da classe Object, com o intuito de mostrar a posição
	 * de uma peça de damas. Como a posição é junto, ex: A2, precisamos do
	 * abre aspas para forçar uma concatenação de strings.
	 */
	@Override
	public String toString() {
		return "" + column + row; 
		
	}
}
