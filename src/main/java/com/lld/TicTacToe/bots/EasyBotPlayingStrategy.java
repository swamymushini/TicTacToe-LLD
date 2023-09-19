package com.lld.TicTacToe.bots;

import java.util.List;

import org.springframework.stereotype.Component;

import com.lld.TicTacToe.model.Cell;
import com.lld.TicTacToe.model.CellStatus;
import com.lld.TicTacToe.model.Game;

@Component
public class EasyBotPlayingStrategy implements BotPlayingStrategy {

	@Override
	public Cell suggestCell(Game game) {

		List<List<Cell>> cells = game.getBoard().getCellsList();

		for (List<Cell> rows : cells) {

			for (Cell cell : rows) {

				if (cell.getCellStatus().equals(CellStatus.EMPTY)) {
					return cell;
				}
			}

		}

		return null;
	}

}
