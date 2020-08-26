package checkers;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import checkers.pieces.Rock;

public class CheckersMatch {

	//associations
	private Board board;
	
	//constructors
	public CheckersMatch() {
		board = new Board(8, 8);    //instanciamos um tabuleiro nulo 8x8
		initialSetup();   //inicializamos as peças
	}
	
	//methods	
	public CheckersPiece[][] getPieces() {
		CheckersPiece[][] mat = new CheckersPiece[board.getRows()][board.getColumns()];  //cria uma matriz nula do mesmo tamanho do tabuleiro
		for(int i = 0; i < mat.length; i++) {
			for(int j = 0; j < mat[i].length; j++) {
				mat[i][j] = (CheckersPiece)board.piece(i, j);  //transforma a matriz nula do tipo piece em tipo CheckersPiece
			}
		}
		return mat;
	}
	
	public boolean[][] possibleMoves(CheckersPosition sourcePosition) {
		Position position = sourcePosition.toPosition();
		validateSourcePosition(position);
		return board.piece(position).possibleMoves();   //retorna os movimentos possiveis de uma peça
	}
	
	public CheckersPiece checkersMove(CheckersPosition sourcePosition, CheckersPosition targetPosition) {
		Position source = sourcePosition.toPosition();  //converte de posição de damas para posição de matriz
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		validateTargetPosition(source, target);
		Piece capturedPiece = makeMove(source, target);  
		return (CheckersPiece)capturedPiece;
	}
	
	private void validateSourcePosition(Position position) {
		if(!board.thereIsAPiece(position)) {   //se nao tiver peça nessa posição e ja testa se a posição existe
			throw new CheckersException("There is no piece on source position!");  //checkersException é uma boardException 
		}	
		if(!board.piece(position).isThereAnyPossibleMove()) {
			throw new CheckersException("There is no possible moves for the chosen piece!");
		}
	}
	
	private void validateTargetPosition(Position source, Position target) {
		if(!board.piece(source).possibleMove(target)) {
			throw new CheckersException("The chosen piece can't move to target position!");
		}
	}
	
	private Piece makeMove(Position source, Position target) {  //vai ter q alterar no futuro
		Piece p = board.removePiece(source); //retira a peça na posição de origem
		Piece targetPiece = board.removePiece(target);  //retira o nulo do tabuleiro
		
		if((target.getRow() <= source.getRow() - 2) || (target.getRow() >= source.getRow() + 2)) {  //se comer peça
			
			if(target.getRow() > source.getRow() && target.getColumn() > source.getColumn()) {   //sudeste
				Position capture = new Position(source.getRow() + 1, source.getColumn() + 1);
				Piece capturedPiece = board.removePiece(capture);
			}
			else if(target.getRow() > source.getRow() && target.getColumn() < source.getColumn()) {  //sudoeste
				Position capture = new Position(source.getRow() + 1, source.getColumn() - 1);
				Piece capturedPiece = board.removePiece(capture);
			}
			else if(target.getRow() < source.getRow() && target.getColumn() > source.getColumn()) {   //nordeste
				Position capture = new Position(source.getRow() - 1, source.getColumn() + 1);
				Piece capturedPiece = board.removePiece(capture);
			}
			else {  //noroeste
				Position capture = new Position(source.getRow() - 1, source.getColumn() - 1);
				Piece capturedPiece = board.removePiece(capture);
			}
		}
		
		board.placePiece(p, target); //coloco a peça p na posição de destino
		return targetPiece;
	}
	
	private void placeNewPiece(char column, int row, CheckersPiece checkersPiece) {
		board.placePiece(checkersPiece, new CheckersPosition(column, row).toPosition());
	}
	
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
