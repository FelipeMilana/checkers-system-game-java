package checkers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import checkers.pieces.Rock;

public class CheckersMatch {


	/*Instanciação dos atributos, associações, listas de peças
	 *no tabuleiro e de peças capturadas e o contrutor padrão, 
	 *sem parametros, com as inicializações feitas manualmente.
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
	
	/* Métodos getters dos atributos e associações.
	 */
	public int getTurn() {
		return turn;
	}
	
	public Color getCurrentPlayer() {
		return currentPlayer;
	}
	
	/*Método responsável por pegar a matriz do tipo Piece
	 *que é inicializada com a instanciação do board, e transformar
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
	
	/* Método responsável por retornar a matriz de movimentos
	 *possiveis de uma peça. Pegamos a posição de origem, a validamos
	 *e em seguida verificamos se pode haver um movimento de captura com 
	 *aquela peça, pois movimento de captura tem preferência ao movimento
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
	
	/*método responsavel por fazer o movimento em geral e retornar uma peça,
	 *seja ela nula (só andou) ooou não nula(houve captura de peça). Para isso
	 *pegamos as posições de origem e destino da peça, transformamos para o 
	 *tipo Position, e validamos as duas posições. Em seguida fazemos o movimento.
	 */
	public CheckersPiece checkersMove(CheckersPosition sourcePosition, CheckersPosition targetPosition) {
		Position source = sourcePosition.toPosition();  
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		validateTargetPosition(source, target);
		Piece capturedPiece = makeMove(source, target);  
		return (CheckersPiece)capturedPiece;
	}
	
	/*Método responsável por receber uma posição de uma peça, e retornar 
	 *verdadeiro ou falso se essa peça tem movimento de capturar uma peça 
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
	
	/*Método responsável por trocar de turno e consequentemente
	 *trocar a cor do jogador. Utilizamos um condicional ternaria,
	 *onde verifica que se a cor do jogador atual é branco a proxima 
	 *é preta, senao a proxima é branca.
	 */
	public void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	/*Método auxiliar(privado) responsavel por validar uma posição de 
	 *origem.Se algumas dessa verificações forem verdadeiras, é lançada uma
	 *exceção, e NÂO é tratada nesta classe. Primeiro, verifica-se se há uma 
	 *peça naquela posição no tabuleiro, em seguida, se a cor da peça selecionada
	 *é igual a cor do jogador atual, em seguida, se aquela peça tem algum movimento
	 *possivel, e por último, verifica se no tabuleiro existe alguma peça que pode
	 *fazer o moviment de comer, e se aquela peça em questão pode comer.
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
	
	/*Método auxiliar responsável por validar a posição de destino de uma peça,
	 *para isso, pega a peça na posição de origem, e verifica se aquela peça tem
	 *um movimento possivel de capturar ou de andar naquela posição, se não houver
	 *nenhum dos dois, lança uma exceção.
	 */
	private void validateTargetPosition(Position source, Position target) {
		if(!board.piece(source).possibleCatchMove(target) && !board.piece(source).possibleSimpleMove(target)) { 
			throw new CheckersException("The chosen piece can't move to target position!");
		}
	}
	
	/*Método responsável pelo movimento de trocas de peça no tabuleiro. Primeiro
	 *retira-se a peça na posição de origem e na posição de destino. Se a posição 
	 *de destino, 2 casas a mais, quer dizer que a posição de destino é para capturar
	 *uma peça. Assim, se a peça capturada for nula, quer dizer que apenas andou, senao 
	 *quer dizer que capturou. Se capturou, a peça é retirada da lista de peças dispostas
	 *no tabuleiro, e adicionada na lista de peças capturadas. Por fim, eu coloco a peça
	 *retirada da posição de origem na posição de destino.
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
	
	/*Método auxiliar com o intuito de verificar se uma determinada
	 *peça pode fazer o movimento de capturar uma peça adversária
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
	
	/*Método auxiliar responsável por filtrar as listas de peças da cor do jogador 
	 *atual dispostas no tabuleiro, e verificar se alguma peça tem o movimento de captura
	 *ou não.
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
	
	/*Método auxiliar responsável por receber uma posição de damas e adicionar a peça
	 * ao tabuleiro por meio de outro metodo auxiliar, que recebe a posição de matriz 
	 * ao inves da de tabuleiro. Em seguida a peça adicionada, é adicionada na lista 
	 * de peças dispostas no tabuleiro.
	 */
	private void placeNewPiece(char column, int row, CheckersPiece checkersPiece) {
		board.placePiece(checkersPiece, new CheckersPosition(column, row).toPosition());
		piecesOnTheBoard.add(checkersPiece);
	}
	
	/*Método auxiliar na qual são adicionadas as peças iniciais do tabuleiro, por meio
	 * do método auxiliar acima. Este método é inicializado no construtor, no momento da
	 * instanciação de uma partida.
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
