package boardgame;

public class BoardException extends RuntimeException {
	
	/*Cria um numero de vers�o, pois se trava de uma classe herdade
	 * de uma classe de exce��es, e cria um construtor, recebendo uma
	 * mensagem e repassando para a classe pai. A classe pai, � a classe
	 * RuntimeException, e ela foi escolhida, pois ela n�o obrigada a tratar
	 * a exce��o no momento em que ela � lan�ada, voc� pode lan�ar e tratar no
	 * futuro quando for conveniente.
	 */
	private static final long serialVersionUID = 1L;

	public BoardException(String msg) {
		super(msg);
	}

}
