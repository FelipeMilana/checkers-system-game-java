package checkers.pieces;

import boardgame.Board;
import boardgame.Position;
import checkers.CheckersPiece;
import checkers.Color;

public class Rock extends CheckersPiece{

	//constructors
	public Rock(Board board, Color color) {  
		super(board, color);
	}
	
	//method
	@Override
	public String toString() {
		return "O";
	}
	
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];  //inicialmente com false
		
		Position p = new Position(0, 0); //posição auxiliar
		
		if(getColor() == Color.WHITE) {
			
			//nordeste
			p.setValues(position.getRow() - 1, position.getColumn() + 1);  
			
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {     //andar
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {      //comer 
				p.setValues(p.getRow() - 1, p.getColumn() + 1);        
				
				if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
					mat[p.getRow()][p.getColumn()] = true;
				}
			}
			
			//noroeste
			p.setValues(position.getRow() - 1, position.getColumn() - 1);  
			
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {     //andar
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {      //comer 
				p.setValues(p.getRow() - 1, p.getColumn() - 1);        
				
				if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
					mat[p.getRow()][p.getColumn()] = true;
				}
			}
			
			//sudoeste 
			p.setValues(position.getRow() + 1, position.getColumn() - 1);
			
			if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {      //comer para tras
				p.setValues(p.getRow() + 1, p.getColumn() - 1);        
				
				if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
					mat[p.getRow()][p.getColumn()] = true;
				}
			}
			
			//sudeste 
			p.setValues(position.getRow() + 1, position.getColumn() + 1);
			
			if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {      //comer para tras
				p.setValues(p.getRow() + 1, p.getColumn() + 1);        
				
				if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
					mat[p.getRow()][p.getColumn()] = true;
				}
			}
		}
		
		else {
			
			//sudeste
			p.setValues(position.getRow() + 1, position.getColumn() + 1);  //4,3
			
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {  //andar
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			if(getBoard().positionExists(p) && !isThereOpponentPiece(p)) {    //4,3
				p.setValues(p.getRow() + 1, p.getColumn() + 1);        
				
				if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {  //5,4
					mat[p.getRow()][p.getColumn()] = true;
				}
			}
			
			//sudoeste
			p.setValues(position.getRow() + 1, position.getColumn() - 1);  
			
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {  //andar
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {      //comer 
				p.setValues(p.getRow() + 1, p.getColumn() - 1);        
				
				if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
					mat[p.getRow()][p.getColumn()] = true;
				}
			}
			
			//nordeste (comer pra tras)
			p.setValues(position.getRow() - 1, position.getColumn() + 1);  
			
			if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {      //comer para tras
				p.setValues(p.getRow() - 1, p.getColumn() + 1);        
				
				if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
					mat[p.getRow()][p.getColumn()] = true;
				}
			}
			
			//noroeste
			p.setValues(position.getRow() - 1, position.getColumn() - 1);  
			
			if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {      //comer para tras
				p.setValues(p.getRow() - 1, p.getColumn() - 1);        
				
				if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
					mat[p.getRow()][p.getColumn()] = true;
				}
			}
		}
		return mat;
	}
}
