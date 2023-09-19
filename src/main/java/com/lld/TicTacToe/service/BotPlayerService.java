package com.lld.TicTacToe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lld.TicTacToe.bots.BotPlayingStrategy;
import com.lld.TicTacToe.model.BotPlayer;
import com.lld.TicTacToe.model.Cell;
import com.lld.TicTacToe.model.Game;

@Component
public class BotPlayerService extends PlayerService {

	@Autowired
	private BotPlayingStrategyFactory botPlayingStrategyFactory;

	@Override
	public Cell createMove(Game game) {

		BotPlayer player = (BotPlayer) game.getCurrentPlayer();
		BotPlayingStrategy botPlayingStrategy = botPlayingStrategyFactory
				.getBotPlayingStrategy(player.getDifficultyLevel());

		Cell cell = botPlayingStrategy.suggestCell(game);

		System.out.println("Bot has done the move");

		return cell;
	}

}
