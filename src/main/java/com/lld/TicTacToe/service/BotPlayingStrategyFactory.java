package com.lld.TicTacToe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lld.TicTacToe.bots.BotPlayingStrategy;
import com.lld.TicTacToe.model.BotDifficultyLevel;

@Component
public class BotPlayingStrategyFactory {

	@Autowired
	private BotPlayingStrategy easyBotPlayingStrategy;

	@Autowired
	private BotPlayingStrategy mediumBotPlayingStrategy;

	public BotPlayingStrategy getBotPlayingStrategy(BotDifficultyLevel botDifficultyLevel) {

		switch (botDifficultyLevel) {
		case EASY:
			return easyBotPlayingStrategy;
		case MEDIUM:
			return mediumBotPlayingStrategy;
		default:
			break;
		}
		return easyBotPlayingStrategy;
	}
}