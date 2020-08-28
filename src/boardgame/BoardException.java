package boardgame;

public class BoardException extends RuntimeException {
	
	/*Cria um numero de versão, pois se trava de uma classe herdade
	 * de uma classe de exceções, e cria um construtor, recebendo uma
	 * mensagem e repassando para a classe pai. A classe pai, é a classe
	 * RuntimeException, e ela foi escolhida, pois ela não obrigada a tratar
	 * a exceção no momento em que ela é lançada, você pode lançar e tratar no
	 * futuro quando for conveniente.
	 */
	private static final long serialVersionUID = 1L;

	public BoardException(String msg) {
		super(msg);
	}

}
