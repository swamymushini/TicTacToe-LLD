package com.lld.TicTacToe.service;

import java.util.Scanner;

import com.lld.TicTacToe.model.Cell;
import com.lld.TicTacToe.model.CellStatus;
import com.lld.TicTacToe.model.Game;
import com.lld.TicTacToe.model.Move;
import com.lld.TicTacToe.model.Player;

public abstract class PlayerService {

	Scanner sc = new Scanner(System.in);

	public abstract Cell createMove(Game game);

	public Move makeMove(Game game) {

		Player player = game.getCurrentPlayer();

		Cell cell = createMove(game);
		if (cell == null || cell.getCellStatus().equals(CellStatus.OCCUPIED)) {
			System.out.println("Current cell is occupied");
			return null;
		}

		cell.setPlayer(player);
		cell.setCellStatus(CellStatus.OCCUPIED);

		int size = game.getMoves().size();
		Move move = new Move(size + 1, cell);
		return move;
	}

}
