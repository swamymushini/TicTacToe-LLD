package com.lld.TicTacToe.bots;

import com.lld.TicTacToe.model.Cell;
import com.lld.TicTacToe.model.Game;

public interface BotPlayingStrategy {
	public Cell suggestCell(Game game);
}
