package com.lld.TicTacToe.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Move {
	
    private int moveNumber;
	private Cell cell;
	
	
}
