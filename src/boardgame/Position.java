package boardgame;

public class Position {  //posi��o no tabuleiro

	/*Instancia��o dos atributos e construtores.
	 */
	private int row;
	private int column;

	public Position(int row, int column) {
		this.row = row;
		this.column = column;
	}

	/*M�todos getters e setters dos atributos.
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
	
	/*M�todo respons�vel por atualizar os valores de linha e
	 * coluna de uma determinada posi��o.
	 */
	public void setValues(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	/*M�todo respons�vel por imprimir a posi��o e � uma sobreposi��o
	 *do m�todo toString da classe Object.
	 */
	@Override  
	public String toString() {
		return row + "," + column;
	}
}
