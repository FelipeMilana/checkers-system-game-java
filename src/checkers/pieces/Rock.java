package checkers.pieces;

import boardgame.Board;
import boardgame.Position;
import checkers.CheckersPiece;
import checkers.Color;

public class Rock extends CheckersPiece{

	/*subclasse de checkersPiece e consequentemente subclasse
	 * de Piece, e por isso tem acesso a todos os seus métodos 
	 * diretamente sem precisar de objetos. Instanciação do 
	 * construtor da superclasse somente, pois não há atributos
	 * e nem associações.
	 */
	public Rock(Board board, Color color) {  
		super(board, color);
	}
	
	/*Método sobreposto, com o intuito de retornar a letra da peça.
	 */
	@Override
	public String toString() {
		return "O";
	}
	
	/*Método sobreposto do método abstrato da classe Piece. Neste método
	 * iremos criar uma matriz com as mesmas dimensões da matriz de peças 
	 * disponíveis no tabuleiro. Utilizaremos uma posição p auxiliar, em 
	 * seguida verificamos os possíveis movimentos de captura para as direções 
	 * nordeste, noroeste, sudeste e sudoeste.Além disso para que seja possível 
	 * um movimento de captura, na direção da peça tem que ter uma peça inimiga, 
	 * e essa posição tem que existir.Se verificado, verifica a casa na frente da 
	 * peça inimiga, se ela estiver vazia e existir, é que é possível capturar.
	 */
	@Override
	public boolean[][] possibleCatchMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0, 0); 
		
		//nordeste
		p.setValues(position.getRow() - 1, position.getColumn() + 1);  
		
		if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {      
			p.setValues(p.getRow() - 1, p.getColumn() + 1);        
			
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
		}
	
		//noroeste
		p.setValues(position.getRow() - 1, position.getColumn() - 1);  
		
		if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {      
			p.setValues(p.getRow() - 1, p.getColumn() - 1);        
			
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
		}
		
		//sudoeste 
		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		
		if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {      
			p.setValues(p.getRow() + 1, p.getColumn() - 1);        
			
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
		}
		
		//sudeste 
		p.setValues(position.getRow() + 1, position.getColumn() + 1);
		
		if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {      
			p.setValues(p.getRow() + 1, p.getColumn() + 1);        
			
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
		}
		return mat;
	}
	
	/*Método sobreposto do método abstrato da classe Piece. Neste método
	 * iremos criar uma matriz com as mesmas dimensões da matriz de peças 
	 * disponíveis no tabuleiro. Utilizaremos uma posição p auxiliar, em 
	 * seguida verificamos a cor da peça, e depois os possíveis movimentos
	 * de andar, para as direções nordeste, noroeste, se a peça for branca
	 * e sudeste e sudoeste, se a peça for preta, pois não é possível andar
	 * para trás. Além disso para que seja possível um movimento de andar, 
	 * na frente da peça na mesma direção, tem que ter uma posição vazia e 
	 * essa posição temque existir. Se for verificado o movimento é possível.
	 */
	@Override
	public boolean[][] possibleSimpleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];  
		
		
		Position p = new Position(0, 0); 
		
		if(getColor() == Color.WHITE) {
			//nordeste
			p.setValues(position.getRow() - 1, position.getColumn() + 1);  
			
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {     
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			//nororeste
			p.setValues(position.getRow() - 1, position.getColumn() - 1); 
			
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {     
				mat[p.getRow()][p.getColumn()] = true;
			}
		}
		else {
			//sudeste
			p.setValues(position.getRow() + 1, position.getColumn() + 1);  
			
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {     
				mat[p.getRow()][p.getColumn()] = true;
			}

			//sudoeste
			p.setValues(position.getRow() + 1, position.getColumn() - 1);  
			
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {     
				mat[p.getRow()][p.getColumn()] = true;
			}
		}
		return mat;
	}
}
