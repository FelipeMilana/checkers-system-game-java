package checkers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import checkers.pieces.Rock;

public class CheckersMatch {


	/*Instancia��o dos atributos, associa��es, listas de pe�as
	 *no tabuleiro e de pe�as capturadas e o contrutor padr�o, 
	 *sem parametros, com as inicializa��es feitas manualmente.
	 */
	private int turn;
	
	private Board board;
	private Color currentPlayer;
	private List<Piece> piecesOnTheBoard = new ArrayList<Piece>();
	private List<Piece> capturedPieces = new ArrayList<Piece>();
	
	public CheckersMatch() {
		board = new Board(8, 8);    
		turn = 1;
		currentPlayer = Color.WHITE;
		initialSetup();   
	}
	
	/* M�todos getters dos atributos e associa��es.
	 */
	public int getTurn() {
		return turn;
	}
	
	public Color getCurrentPlayer() {
		return currentPlayer;
	}
	
	/*M�todo respons�vel por pegar a matriz do tipo Piece
	 *que � inicializada com a instancia��o do board, e transformar
	 *para o tipo CheckersPiece.
	 */
	public CheckersPiece[][] getPieces() {
		CheckersPiece[][] mat = new CheckersPiece[board.getRows()][board.getColumns()];  
		for(int i = 0; i < mat.length; i++) {
			for(int j = 0; j < mat[i].length; j++) {
				mat[i][j] = (CheckersPiece)board.piece(i, j); 
			}
		}
		return mat;
	}
	
	/* M�todo respons�vel por retornar a matriz de movimentos
	 *possiveis de uma pe�a. Pegamos a posi��o de origem, a validamos
	 *e em seguida verificamos se pode haver um movimento de captura com 
	 *aquela pe�a, pois movimento de captura tem prefer�ncia ao movimento
	 *simples de andar. Se for possivel, retorna a matriz de movimentos
	 *de captura, senao retorna a matriz de movimento de andar.
	 */
	public boolean[][] possibleMoves(CheckersPosition sourcePosition) { 
		Position position = sourcePosition.toPosition();
		validateSourcePosition(position);
		if(CanCatch(board.piece(position))) {  
			return board.piece(position).possibleCatchMoves();
		}
		else {
			return board.piece(position).possibleSimpleMoves();  
		}
	}
	
	/*m�todo responsavel por fazer o movimento em geral e retornar uma pe�a,
	 *seja ela nula (s� andou) ooou n�o nula(houve captura de pe�a). Para isso
	 *pegamos as posi��es de origem e destino da pe�a, transformamos para o 
	 *tipo Position, e validamos as duas posi��es. Em seguida fazemos o movimento.
	 */
	public CheckersPiece checkersMove(CheckersPosition sourcePosition, CheckersPosition targetPosition) {
		Position source = sourcePosition.toPosition();  
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		validateTargetPosition(source, target);
		Piece capturedPiece = makeMove(source, target);  
		return (CheckersPiece)capturedPiece;
	}
	
	/*M�todo respons�vel por receber uma posi��o de uma pe�a, e retornar 
	 *verdadeiro ou falso se essa pe�a tem movimento de capturar uma pe�a 
	 *inimiga ou nao.
	 */
	public boolean CanCatch(CheckersPosition checkersPosition) {     
		Piece piece = board.piece(checkersPosition.toPosition());
		boolean[][] matCatch = piece.possibleCatchMoves();
				
		for(int i = 0; i < matCatch.length; i++) {
			for(int j = 0; j < matCatch[i].length; j++) {
				if(matCatch[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
	
	/*M�todo respons�vel por trocar de turno e consequentemente
	 *trocar a cor do jogador. Utilizamos um condicional ternaria,
	 *onde verifica que se a cor do jogador atual � branco a proxima 
	 *� preta, senao a proxima � branca.
	 */
	public void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	/*M�todo auxiliar(privado) responsavel por validar uma posi��o de 
	 *origem.Se algumas dessa verifica��es forem verdadeiras, � lan�ada uma
	 *exce��o, e N�O � tratada nesta classe. Primeiro, verifica-se se h� uma 
	 *pe�a naquela posi��o no tabuleiro, em seguida, se a cor da pe�a selecionada
	 *� igual a cor do jogador atual, em seguida, se aquela pe�a tem algum movimento
	 *possivel, e por �ltimo, verifica se no tabuleiro existe alguma pe�a que pode
	 *fazer o moviment de comer, e se aquela pe�a em quest�o pode comer.
	 */
	
	private void validateSourcePosition(Position position) {
		if(!board.thereIsAPiece(position)) {   
			throw new CheckersException("There is no piece on source position!");   
		}
		if(currentPlayer != ((CheckersPiece)board.piece(position)).getColor()) {
			throw new CheckersException("The chosen piece is not yours!");
		}
		if(!board.piece(position).isThereAnyPossibleMoves()) {
			throw new CheckersException("There is no possible moves for the chosen piece!");
		}
		
		if(isThereAnyPieceCanCatch() && !CanCatch(board.piece(position))) {  
			throw new CheckersException("You must catch the opponent pieces!");
		}
	}
	
	/*M�todo auxiliar respons�vel por validar a posi��o de destino de uma pe�a,
	 *para isso, pega a pe�a na posi��o de origem, e verifica se aquela pe�a tem
	 *um movimento possivel de capturar ou de andar naquela posi��o, se n�o houver
	 *nenhum dos dois, lan�a uma exce��o.
	 */
	private void validateTargetPosition(Position source, Position target) {
		if(!board.piece(source).possibleCatchMove(target) && !board.piece(source).possibleSimpleMove(target)) { 
			throw new CheckersException("The chosen piece can't move to target position!");
		}
	}
	
	/*M�todo respons�vel pelo movimento de trocas de pe�a no tabuleiro. Primeiro
	 *retira-se a pe�a na posi��o de origem e na posi��o de destino. Se a posi��o 
	 *de destino, 2 casas a mais, quer dizer que a posi��o de destino � para capturar
	 *uma pe�a. Assim, se a pe�a capturada for nula, quer dizer que apenas andou, senao 
	 *quer dizer que capturou. Se capturou, a pe�a � retirada da lista de pe�as dispostas
	 *no tabuleiro, e adicionada na lista de pe�as capturadas. Por fim, eu coloco a pe�a
	 *retirada da posi��o de origem na posi��o de destino.
	 */
	private Piece makeMove(Position source, Position target) { 
		Piece p = board.removePiece(source); 
		Piece targetPiece = board.removePiece(target);  
		Piece capturedPiece = targetPiece;
		
		if((target.getRow() <= source.getRow() - 2) || (target.getRow() >= source.getRow() + 2)) {  
			
			if(target.getRow() > source.getRow() && target.getColumn() > source.getColumn()) {   
				Position capture = new Position(source.getRow() + 1, source.getColumn() + 1);
				capturedPiece = board.removePiece(capture);
			}
			else if(target.getRow() > source.getRow() && target.getColumn() < source.getColumn()) {  
				Position capture = new Position(source.getRow() + 1, source.getColumn() - 1);
				capturedPiece = board.removePiece(capture);
			}
			else if(target.getRow() < source.getRow() && target.getColumn() > source.getColumn()) {   
				Position capture = new Position(source.getRow() - 1, source.getColumn() + 1);
				capturedPiece = board.removePiece(capture);
			}
			else {  
				Position capture = new Position(source.getRow() - 1, source.getColumn() - 1);
				capturedPiece = board.removePiece(capture);
			}
		}
		
		if(capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		
		board.placePiece(p, target); 
		return capturedPiece;
	}
	
	/*M�todo auxiliar com o intuito de verificar se uma determinada
	 *pe�a pode fazer o movimento de capturar uma pe�a advers�ria
	 */
	private boolean CanCatch(Piece piece) {     
		boolean[][] matCatch = piece.possibleCatchMoves();
				
		for(int i = 0; i < matCatch.length; i++) {
			for(int j = 0; j < matCatch[i].length; j++) {
				if(matCatch[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
	
	/*M�todo auxiliar respons�vel por filtrar as listas de pe�as da cor do jogador 
	 *atual dispostas no tabuleiro, e verificar se alguma pe�a tem o movimento de captura
	 *ou n�o.
	 */
	private boolean isThereAnyPieceCanCatch() {
		List<Piece> myPieces = piecesOnTheBoard.stream().filter(x -> ((CheckersPiece)x).getColor() == currentPlayer).collect(Collectors.toList());
		for(Piece p: myPieces) {
			boolean[][] mat = p.possibleCatchMoves();
			for(int i = 0; i < mat.length; i++) {
				for(int j = 0; j < mat[i].length; j++) {
					if(mat[i][j]) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/*M�todo auxiliar respons�vel por receber uma posi��o de damas e adicionar a pe�a
	 * ao tabuleiro por meio de outro metodo auxiliar, que recebe a posi��o de matriz 
	 * ao inves da de tabuleiro. Em seguida a pe�a adicionada, � adicionada na lista 
	 * de pe�as dispostas no tabuleiro.
	 */
	private void placeNewPiece(char column, int row, CheckersPiece checkersPiece) {
		board.placePiece(checkersPiece, new CheckersPosition(column, row).toPosition());
		piecesOnTheBoard.add(checkersPiece);
	}
	
	/*M�todo auxiliar na qual s�o adicionadas as pe�as iniciais do tabuleiro, por meio
	 * do m�todo auxiliar acima. Este m�todo � inicializado no construtor, no momento da
	 * instancia��o de uma partida.
	 */
	private void initialSetup() {
		placeNewPiece('b', 8, new Rock(board, Color.BLACK));
		placeNewPiece('d', 8, new Rock(board, Color.BLACK));
		placeNewPiece('f', 8, new Rock(board, Color.BLACK));
		placeNewPiece('h', 8, new Rock(board, Color.BLACK));
		placeNewPiece('a', 7, new Rock(board, Color.BLACK));
		placeNewPiece('c', 7, new Rock(board, Color.BLACK));
		placeNewPiece('e', 7, new Rock(board, Color.BLACK));
		placeNewPiece('g', 7, new Rock(board, Color.BLACK));
		placeNewPiece('b', 6, new Rock(board, Color.BLACK));
		placeNewPiece('d', 6, new Rock(board, Color.BLACK));
		placeNewPiece('f', 6, new Rock(board, Color.BLACK));
		placeNewPiece('h', 6, new Rock(board, Color.BLACK));
		
		placeNewPiece('a', 3, new Rock(board, Color.WHITE));
		placeNewPiece('c', 3, new Rock(board, Color.WHITE));
		placeNewPiece('e', 3, new Rock(board, Color.WHITE));
		placeNewPiece('g', 3, new Rock(board, Color.WHITE));
		placeNewPiece('b', 2, new Rock(board, Color.WHITE));
		placeNewPiece('d', 2, new Rock(board, Color.WHITE));
		placeNewPiece('f', 2, new Rock(board, Color.WHITE));
		placeNewPiece('h', 2, new Rock(board, Color.WHITE));
		placeNewPiece('a', 1, new Rock(board, Color.WHITE));
		placeNewPiece('c', 1, new Rock(board, Color.WHITE));
		placeNewPiece('e', 1, new Rock(board, Color.WHITE));
		placeNewPiece('g', 1, new Rock(board, Color.WHITE));
	}
}
