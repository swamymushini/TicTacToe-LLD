package com.lld.TicTacToe.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BotPlayer extends Player {

	private BotDifficultyLevel difficultyLevel;

	public BotPlayer(BotDifficultyLevel difficultyLevel) {
		super("Bot", PlayerType.BOT_PLAYER, 'B');
		this.difficultyLevel = difficultyLevel;
	}

}
