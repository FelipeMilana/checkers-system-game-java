package checkers.pieces;

import boardgame.Board;
import boardgame.Position;
import checkers.CheckersPiece;
import checkers.Color;

public class KingRock extends CheckersPiece {

	/*subclasse de checkersPiece e consequentemente subclasse
	 * de Piece, e por isso tem acesso a todos os seus m�todos 
	 * diretamente sem precisar de objetos. Instancia��o do 
	 * construtor da superclasse somente, pois n�o h� atributos
	 * e nem associa��es.
	 */
	public KingRock(Board board, Color color) {
		super(board, color);
	}
	
	/*M�todo sobreposto, com o intuito de retornar a letra da pe�a.
	 */
	@Override
	public String toString() {
		return "K";
	}
	
	/*M�todo sobreposto do m�todo abstrato da classe Piece. Neste m�todo
	 * iremos criar uma matriz com as mesmas dimens�es da matriz de pe�as 
	 * dispon�veis no tabuleiro. Utilizaremos uma posi��o p auxiliar, em 
	 * seguida verificamos os poss�veis movimentos de captura para as dire��es 
	 * nordeste, noroeste, sudeste e sudoeste.Al�m disso para que seja poss�vel 
	 * um movimento de captura, a pe�a anda quandas casas quiser at� que seja 
	 * encontrada na dire��o da pe�a, uma pe�a inimiga, e essa posi��o tem que 
	 * existir.Se verificado, verifica as possiveis casas vazias na dire��o da
	 * pe�a inimiga , se ela estiver vazia e existir, � que � poss�vel capturar
	 * e parar ali.
	 */
	@Override
	public boolean[][] possibleCatchMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0, 0);
					
		//nordeste
		p.setValues(position.getRow() - 1 , position.getColumn() + 1); 
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {   
			p.setValues(p.getRow() - 1, p.getColumn() + 1);
		}
		if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {  
			
			p.setValues(p.getRow() - 1, p.getColumn() + 1);
			
			while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {  
				mat[p.getRow()][p.getColumn()] = true;
				p.setValues(p.getRow() - 1, p.getColumn() + 1);
			}
		}
		
		//noroeste
		p.setValues(position.getRow() - 1 , position.getColumn() - 1); 
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {   
			p.setValues(p.getRow() - 1, p.getColumn() - 1);
		}
		if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {  
			
			p.setValues(p.getRow() - 1, p.getColumn() - 1);
			
			while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {  
				mat[p.getRow()][p.getColumn()] = true;
				p.setValues(p.getRow() - 1, p.getColumn() - 1);
			}
		}
		
		//sudeste
		p.setValues(position.getRow() + 1 , position.getColumn() + 1); 
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {   
			p.setValues(p.getRow() + 1, p.getColumn() + 1);
		}
		if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {  
			
			p.setValues(p.getRow() + 1, p.getColumn() + 1);
			
			while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {  
				mat[p.getRow()][p.getColumn()] = true;
				p.setValues(p.getRow() + 1, p.getColumn() + 1);
			}
		}
		
		//sudoeste
		p.setValues(position.getRow() + 1 , position.getColumn() - 1); 
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {   
			p.setValues(p.getRow() + 1, p.getColumn() - 1);
		}
		if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {  
			
			p.setValues(p.getRow() + 1, p.getColumn() - 1);
			
			while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {  
				mat[p.getRow()][p.getColumn()] = true;
				p.setValues(p.getRow() + 1, p.getColumn() - 1);
			}
		}
		return mat;
	}
	
	/*Uma dama pode andar em uma dire��o quantas casas quiser, Assim, aanalisaremos os
	 * poss�veis movimentos de andar de uma dama, nas 4 dire��es poss�veis, noroeste, 
	 * nordeste, sudeste e sudoeste. Primeiramente, inicia-se uma matriz falsa do tamanho 
	 * do tabuleiro, e pega-se uma posi��o auxiliar p. Em seguida testamos cada posi��o 
	 * na dire��o da dama, verificando se h� ou n�o uma pe�a na sua dire��o. Se houver uma
	 * pe�a, verificar se � sua, pois assim, n�o tem problema, por�m se for do oponente,
	 * � obrigado a ter uma pe�a na casa da frente, ou a cadsa da frente n�o existir,
	 * impossibilitando o movimento de captura.
	 */
	public boolean[][] possibleSimpleMoves() {
		
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0, 0);
					
		//nordeste
		p.setValues(position.getRow() - 1 , position.getColumn() + 1); 
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {   
			p.setValues(p.getRow() - 1, p.getColumn() + 1);
		}
		
		if(getBoard().positionExists(p) && getBoard().thereIsAPiece(p)) { 
			if(isThereOpponentPiece(p)) {
				p.setValues(p.getRow() - 1, p.getColumn() + 1);
				
				if(getBoard().positionExists(p) && getBoard().thereIsAPiece(p)) {
					Position p1 = new Position(0, 0);
					p1.setValues(position.getRow() - 1, position.getColumn() + 1);
					
					while(getBoard().positionExists(p1) && !getBoard().thereIsAPiece(p1)) {  
						mat[p1.getRow()][p1.getColumn()] = true;
						p1.setValues(p1.getRow() - 1, p1.getColumn() + 1);
					}
				}
			}
			else {
				Position p1 = new Position(0, 0);
				p1.setValues(position.getRow() - 1, position.getColumn() + 1);
				
				while(getBoard().positionExists(p1) && !getBoard().thereIsAPiece(p1)) {  
					mat[p1.getRow()][p1.getColumn()] = true;
					p1.setValues(p1.getRow() - 1, p1.getColumn() + 1);
				}
			}
		}
		
		//noroeste
		p.setValues(position.getRow() - 1 , position.getColumn() - 1); 
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {   
			p.setValues(p.getRow() + 1, p.getColumn() + 1);
		}
		
		if(getBoard().positionExists(p) && getBoard().thereIsAPiece(p)) { 
			if(isThereOpponentPiece(p)) {
				p.setValues(p.getRow() - 1, p.getColumn() - 1);
				
				if(getBoard().positionExists(p) && getBoard().thereIsAPiece(p)) {
					Position p1 = new Position(0, 0);
					p1.setValues(position.getRow() - 1, position.getColumn() - 1);
					
					while(getBoard().positionExists(p1) && !getBoard().thereIsAPiece(p1)) {  
						mat[p1.getRow()][p1.getColumn()] = true;
						p1.setValues(p1.getRow() - 1, p1.getColumn() - 1);
					}
				}
			}
			else {
				Position p1 = new Position(0, 0);
				p1.setValues(position.getRow() - 1, position.getColumn() - 1);
				
				while(getBoard().positionExists(p1) && !getBoard().thereIsAPiece(p1)) {  
					mat[p1.getRow()][p1.getColumn()] = true;
					p1.setValues(p1.getRow() - 1, p1.getColumn() - 1);
				}
			}
		}
		
		//sudeste
		p.setValues(position.getRow() + 1 , position.getColumn() + 1); 
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {   
			p.setValues(p.getRow() + 1, p.getColumn() + 1);
		}
		
		if(getBoard().positionExists(p) && getBoard().thereIsAPiece(p)) { 
			if(isThereOpponentPiece(p)) {
				p.setValues(p.getRow() + 1, p.getColumn() + 1);
				
				if(getBoard().positionExists(p) && getBoard().thereIsAPiece(p)) {
					Position p1 = new Position(0, 0);
					p1.setValues(position.getRow() + 1, position.getColumn() + 1);
					
					while(getBoard().positionExists(p1) && !getBoard().thereIsAPiece(p1)) {  
						mat[p1.getRow()][p1.getColumn()] = true;
						p1.setValues(p1.getRow() + 1, p1.getColumn() + 1);
					}
				}
			}
			else {
				Position p1 = new Position(0, 0);
				p1.setValues(position.getRow() + 1, position.getColumn() + 1);
				
				while(getBoard().positionExists(p1) && !getBoard().thereIsAPiece(p1)) {  
					mat[p1.getRow()][p1.getColumn()] = true;
					p1.setValues(p1.getRow() + 1, p1.getColumn() + 1);
				}
			}
		}
		
		//sudoeste
		p.setValues(position.getRow() + 1 , position.getColumn() - 1); 
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {   
			p.setValues(p.getRow() + 1, p.getColumn() - 1);
		}
		
		if(getBoard().positionExists(p) && getBoard().thereIsAPiece(p)) { 
			if(isThereOpponentPiece(p)) {
				p.setValues(p.getRow() + 1, p.getColumn() - 1);
				
				if(getBoard().positionExists(p) && getBoard().thereIsAPiece(p)) {
					Position p1 = new Position(0, 0);
					p1.setValues(position.getRow() + 1, position.getColumn() - 1);
					
					while(getBoard().positionExists(p1) && !getBoard().thereIsAPiece(p1)) {  
						mat[p1.getRow()][p1.getColumn()] = true;
						p1.setValues(p1.getRow() + 1, p1.getColumn() - 1);
					}
				}
			}
			else {
				Position p1 = new Position(0, 0);
				p1.setValues(position.getRow() + 1, position.getColumn() - 1);
				
				while(getBoard().positionExists(p1) && !getBoard().thereIsAPiece(p1)) {  
					mat[p1.getRow()][p1.getColumn()] = true;
					p1.setValues(p1.getRow() + 1, p1.getColumn() - 1);
				}
			}
		}
		return mat;
	}
}
