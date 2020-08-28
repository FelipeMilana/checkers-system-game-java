package checkers;

import boardgame.BoardException;

public class CheckersException extends BoardException {   
	
	/*Cria um numero de vers�o, pois se trava de uma classe herdade
	 * de uma classe de exce��es, e cria um construtor, recebendo uma
	 * mensagem e repassando para a classe pai. A classe pai, � a classe
	 * BoardException, e ela foi escolhida, pois ao tratarmos esse tipo
	 * de exce��o, iremos estar tratando tamb�m uma BoardExceptiom, o que 
	 * facilita.
	 */	
	private static final long serialVersionUID = 1L;

	public CheckersException(String msg) {
		super(msg);
	}

}
