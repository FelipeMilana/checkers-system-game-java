package checkers;

import boardgame.BoardException;

public class CheckersException extends BoardException {   //pois se tratarmos uma CheckersException tratamos uma boardException tbm 
	private static final long serialVersionUID = 1L;

	//constructors
	public CheckersException(String msg) {
		super(msg);
	}

}
