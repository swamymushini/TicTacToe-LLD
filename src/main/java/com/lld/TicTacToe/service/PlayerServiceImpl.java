package com.lld.TicTacToe.service;

import org.springframework.stereotype.Component;

import com.lld.TicTacToe.model.Cell;
import com.lld.TicTacToe.model.Game;
import com.lld.TicTacToe.model.Player;

@Component
public class PlayerServiceImpl extends PlayerService {

	public Cell createMove(Game game) {

		Player player =game.getCurrentPlayer();
		
		System.out.println("Enter row and column of the cell for the player " + player.getName());

		int row = sc.nextInt();
		if (row >= game.getBoard().getRowsNum()) {
			System.out.println("Invalid input");
			return null;
		}

		int col = sc.nextInt();
		if (col >= game.getBoard().getColsNum()) {
			System.out.println("Invalid input");
			return null;
		}

		Cell cell = game.getBoard().getCellsList().get(row).get(col);

		return cell;
	}

}
