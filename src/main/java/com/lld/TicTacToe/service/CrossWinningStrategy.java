package com.lld.TicTacToe.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.lld.TicTacToe.model.Board;
import com.lld.TicTacToe.model.Cell;
import com.lld.TicTacToe.model.CellStatus;
import com.lld.TicTacToe.model.Move;
import com.lld.TicTacToe.model.Player;

@Component
public class CrossWinningStrategy implements WinningStrategy {

	@Override
	public boolean checkWinner(Board board, Move move) {

		List<List<Cell>> cellsList = board.getCellsList();
		Cell cell = move.getCell();
		Player player = cell.getPlayer();
		int colsNum = board.getColsNum();

		boolean match = true;

		for (int i = 0; i < cellsList.size(); i++) {

			Cell cell2 = cellsList.get(i).get(i);

			if (cell2.getCellStatus().equals(CellStatus.EMPTY)) {
				match = false;
				break;
			}

			if (cell2.getPlayer() != player) {
				match = false;
				break;
			}

		}

		if (match)
			return true;

		for (int i = 0; i < cellsList.size(); i++) {

			Cell cell2 = cellsList.get(i).get(colsNum - i - 1);

			if (cell2.getCellStatus().equals(CellStatus.EMPTY))
				return match;

			if (cell2.getPlayer() != player)
				return match;

		}

		return true;
	}

}
