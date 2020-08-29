package checkers.pieces;

import boardgame.Board;
import boardgame.Position;
import checkers.CheckersPiece;
import checkers.Color;

public class Rock extends CheckersPiece{

	/*subclasse de checkersPiece e consequentemente subclasse
	 * de Piece, e por isso tem acesso a todos os seus m�todos 
	 * diretamente sem precisar de objetos. Instancia��o do 
	 * construtor da superclasse somente, pois n�o h� atributos
	 * e nem associa��es.
	 */
	public Rock(Board board, Color color) {  
		super(board, color);
	}
	
	/*M�todo sobreposto, com o intuito de retornar a letra da pe�a.
	 */
	@Override
	public String toString() {
		return "O";
	}
	
	/*M�todo sobreposto do m�todo abstrato da classe Piece. Neste m�todo
	 * iremos criar uma matriz com as mesmas dimens�es da matriz de pe�as 
	 * dispon�veis no tabuleiro. Utilizaremos uma posi��o p auxiliar, em 
	 * seguida verificamos os poss�veis movimentos de captura para as dire��es 
	 * nordeste, noroeste, sudeste e sudoeste.Al�m disso para que seja poss�vel 
	 * um movimento de captura, na dire��o da pe�a tem que ter uma pe�a inimiga, 
	 * e essa posi��o tem que existir.Se verificado, verifica a casa na frente da 
	 * pe�a inimiga, se ela estiver vazia e existir, � que � poss�vel capturar.
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
	
	/*M�todo sobreposto do m�todo abstrato da classe Piece. Neste m�todo
	 * iremos criar uma matriz com as mesmas dimens�es da matriz de pe�as 
	 * dispon�veis no tabuleiro. Utilizaremos uma posi��o p auxiliar, em 
	 * seguida verificamos a cor da pe�a, e depois os poss�veis movimentos
	 * de andar, para as dire��es nordeste, noroeste, se a pe�a for branca
	 * e sudeste e sudoeste, se a pe�a for preta, pois n�o � poss�vel andar
	 * para tr�s. Al�m disso para que seja poss�vel um movimento de andar, 
	 * na frente da pe�a na mesma dire��o, tem que ter uma posi��o vazia e 
	 * essa posi��o temque existir. Se for verificado o movimento � poss�vel.
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
