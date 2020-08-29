package checkers.pieces;

import boardgame.Board;
import boardgame.Position;
import checkers.CheckersPiece;
import checkers.Color;

public class KingRock extends CheckersPiece {

	/*subclasse de checkersPiece e consequentemente subclasse
	 * de Piece, e por isso tem acesso a todos os seus métodos 
	 * diretamente sem precisar de objetos. Instanciação do 
	 * construtor da superclasse somente, pois não há atributos
	 * e nem associações.
	 */
	public KingRock(Board board, Color color) {
		super(board, color);
	}
	
	/*Método sobreposto, com o intuito de retornar a letra da peça.
	 */
	@Override
	public String toString() {
		return "K";
	}
	
	/*Método sobreposto do método abstrato da classe Piece. Neste método
	 * iremos criar uma matriz com as mesmas dimensões da matriz de peças 
	 * disponíveis no tabuleiro. Utilizaremos uma posição p auxiliar, em 
	 * seguida verificamos os possíveis movimentos de captura para as direções 
	 * nordeste, noroeste, sudeste e sudoeste.Além disso para que seja possível 
	 * um movimento de captura, a peça anda quandas casas quiser até que seja 
	 * encontrada na direção da peça, uma peça inimiga, e essa posição tem que 
	 * existir.Se verificado, verifica as possiveis casas vazias na direção da
	 * peça inimiga , se ela estiver vazia e existir, é que é possível capturar
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
	
	/*Uma dama pode andar em uma direção quantas casas quiser, Assim, aanalisaremos os
	 * possíveis movimentos de andar de uma dama, nas 4 direções possíveis, noroeste, 
	 * nordeste, sudeste e sudoeste. Primeiramente, inicia-se uma matriz falsa do tamanho 
	 * do tabuleiro, e pega-se uma posição auxiliar p. Em seguida testamos cada posição 
	 * na direção da dama, verificando se há ou não uma peça na sua direção. Se houver uma
	 * peça, verificar se é sua, pois assim, não tem problema, porém se for do oponente,
	 * é obrigado a ter uma peça na casa da frente, ou a cadsa da frente não existir,
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
