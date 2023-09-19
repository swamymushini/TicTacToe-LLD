package com.lld.TicTacToe.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BotPlayer extends Player {

	private BotDifficultyLevel difficultyLevel;

	public BotPlayer(String name, BotDifficultyLevel difficultyLevel, char symbol) {
		super(name, PlayerType.BOT_PLAYER, symbol);
		this.difficultyLevel = difficultyLevel;
	}

}
