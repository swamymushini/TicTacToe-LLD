package com.lld.TicTacToe.service;

import com.lld.TicTacToe.model.Board;

public interface GameService {

	void intiateGame();
	
	Board initiateBoard(int rowNum, int colNum);
	
}
