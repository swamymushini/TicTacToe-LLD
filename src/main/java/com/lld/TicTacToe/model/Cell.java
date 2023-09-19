package com.lld.TicTacToe.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cell {

	private int row;
	private int col;
	private Player player;
    private CellStatus cellStatus = CellStatus.EMPTY;

	public Cell(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}

}
