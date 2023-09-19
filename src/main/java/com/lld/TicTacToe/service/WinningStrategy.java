package com.lld.TicTacToe.service;

import com.lld.TicTacToe.model.Board;
import com.lld.TicTacToe.model.Move;

public interface WinningStrategy {

    boolean checkWinner(Board board, Move move);
	
}
