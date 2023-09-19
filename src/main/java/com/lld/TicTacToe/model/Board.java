package com.lld.TicTacToe.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Board {

	private List<List<Cell>> cellsList = new ArrayList<>();
	private int rowsNum;
	private int colsNum;
	
	public Board(int rowsNum, int colsNum) {
		super();
		this.rowsNum = rowsNum;
		this.colsNum = colsNum;
	}
	
	

}
