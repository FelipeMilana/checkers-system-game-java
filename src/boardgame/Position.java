package boardgame;

public class Position {  //posição no tabuleiro

	/*Instanciação dos atributos e construtores.
	 */
	private int row;
	private int column;

	public Position(int row, int column) {
		this.row = row;
		this.column = column;
	}

	/*Métodos getters e setters dos atributos.
	 */
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}
	
	/*Método responsável por atualizar os valores de linha e
	 * coluna de uma determinada posição.
	 */
	public void setValues(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	/*Método responsável por imprimir a posição e é uma sobreposição
	 *do método toString da classe Object.
	 */
	@Override  
	public String toString() {
		return row + "," + column;
	}
}
