package com.lld.TicTacToe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lld.TicTacToe.model.PlayerType;

@Component
public class PlayerServiceFactory {

	@Autowired
	private PlayerService playerServiceImpl;

	@Autowired
	private PlayerService botPlayerService;

	public PlayerService getPlayingService(PlayerType playerType) {

		if (playerType.equals(PlayerType.BOT_PLAYER)) {
			return botPlayerService;
		}

		return playerServiceImpl;
	}

}
