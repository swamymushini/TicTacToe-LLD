package com.lld.TicTacToe.bots;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lld.TicTacToe.model.Board;
import com.lld.TicTacToe.model.Cell;
import com.lld.TicTacToe.model.CellStatus;
import com.lld.TicTacToe.model.Game;

@Component
public class MediumBotPlayingStrategy implements BotPlayingStrategy {

	@Autowired
	private OpenAIAPIService openAIAPIService;
	ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public Cell suggestCell(Game game) {
		Cell cell = openAIAPIService.getNextMoveContent(game.getCurrentPlayer(), getBoardStructure(game.getBoard()),
				game.getBoard());
		return cell;
	}

	private StringBuilder getBoardStructure(Board board) {
		StringBuilder formattedBoard = new StringBuilder();
		formattedBoard.append("   0    1   2\n");

		for (int i = 0; i < board.getCellsList().size(); i++) {
			formattedBoard.append(i).append("  |");

			for (int j = 0; j < board.getCellsList().size(); j++) {
				if (board.getCellsList().get(board.getCellsList().size() - i - 1).get(j).getCellStatus()
						.equals(CellStatus.EMPTY)) {
					formattedBoard.append("  |");
				} else {
					formattedBoard.append(board.getCellsList().get(board.getCellsList().size() - i - 1).get(j)
							.getPlayer().getSymbol()).append(" |");
				}
			}
			formattedBoard.append("\n");
		}

		return formattedBoard;
	}

}