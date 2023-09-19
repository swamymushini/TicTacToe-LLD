package com.lld.TicTacToe.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {
	
	private long id;
	private String name;
	private PlayerType playerType;
	private char symbol;

	public Player(String name, PlayerType playerType, char symbol) {
		
		super();
		this.name = name;
		this.playerType = playerType;
		this.symbol = symbol;
	}
	
	
	
}
