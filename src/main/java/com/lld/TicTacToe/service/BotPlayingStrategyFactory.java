package com.lld.TicTacToe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lld.TicTacToe.bots.BotPlayingStrategy;
import com.lld.TicTacToe.bots.EasyBotPlayingStrategy;
import com.lld.TicTacToe.model.BotDifficultyLevel;

@Component
public class BotPlayingStrategyFactory {

	@Autowired
	private EasyBotPlayingStrategy easyBotPlayingStrategy;

	public BotPlayingStrategy getBotPlayingStrategy(BotDifficultyLevel botDifficultyLevel) {

		switch (botDifficultyLevel) {
		case EASY:
			return easyBotPlayingStrategy;
		default:
			break;
		}
		return easyBotPlayingStrategy;
	}
}