package com.lld.TicTacToe.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lld.TicTacToe.model.Board;
import com.lld.TicTacToe.model.BotDifficultyLevel;
import com.lld.TicTacToe.model.BotPlayer;
import com.lld.TicTacToe.model.Cell;
import com.lld.TicTacToe.model.Game;
import com.lld.TicTacToe.model.GameState;
import com.lld.TicTacToe.model.Move;
import com.lld.TicTacToe.model.Player;
import com.lld.TicTacToe.model.PlayerType;

@Component
public class GameServiceImpl implements GameService {

	@Autowired
	private ColumnsWinningStrategy columnsWinningStrategy;

	@Autowired
	private RowsWinningStrategy rowsWinningStrategy;

	@Autowired
	private CrossWinningStrategy crossWinningStrategy;

	@Autowired
	private PlayerServiceFactory playerServiceFactory;

	Scanner sc = new Scanner(System.in);

	public void intiateGame() {
		System.out.println("Lets begin Tic Tac Toe ....");
		System.out.println();

		System.out.println("What dimension of board do you need?");
		int dimension = sc.nextInt();

		Board board = initiateBoard(dimension, dimension);

		System.out.println("Enter the name for First player");
		String name = sc.next();

		System.out.println("Enter the symbol for First player");
		char symbol = sc.next().charAt(0);

		Player p1 = new Player(name, PlayerType.HUMAN, symbol);

		System.out.println("Is there a bot? y/n");

		char isBot = sc.next().charAt(0);
		Player p2 = null;
		if (isBot == 'y') {
			p2 = new BotPlayer(BotDifficultyLevel.EASY);
		} else {
			System.out.println("Enter the name for Second player");

			name = sc.next();

			System.out.println("Enter the symbol for Second player");

			symbol = sc.next().charAt(0);

			p2 = new Player(name, PlayerType.HUMAN, symbol);
		}

		Game game = Game.getBuilder().addPlayer(p1).addPlayer(p2).addWinningStrategy(columnsWinningStrategy)
				.addWinningStrategy(rowsWinningStrategy).addWinningStrategy(crossWinningStrategy)
				.setGameState(GameState.NEW).build();

		game.setGameState(GameState.INPROGRESS);
		game.setBoard(board);

		loop: while (true) {

			printBoard(board);

			Player nextPlayer = game.getCurrentPlayer();

			PlayerService playerService = playerServiceFactory.getPlayingService(nextPlayer.getPlayerType());

			Move move = playerService.makeMove(game);

			if (move == null) {
				continue;
			}

			game.addMove(move);
			game.nextPlayerTurn();

			List<WinningStrategy> winningStrategies = game.getWinningStrategies();

			for (WinningStrategy winningStrategy : winningStrategies) {
				boolean win = winningStrategy.checkWinner(board, move);

				if (win) {
					game.setGameState(GameState.WIN);
					game.setWinner(move.getCell().getPlayer());
					System.out.println("------Game Over------");
					System.out.println(move.getCell().getPlayer().getName() + " is the Winner!!!");
					break loop;
				}

				if (move.getMoveNumber() == dimension * dimension) {
					game.setGameState(GameState.DRAW);
					System.out.println("Game Ended as Draw");
					break loop;
				}
			}
		}

		printBoard(board);
	}

	private void printBoard(Board board) {
		List<List<Cell>> cells = board.getCellsList();

		for (List<Cell> rows : cells) {

			for (Cell cell : rows) {

				System.out.print("|");

				if (cell.getPlayer() == null)
					System.out.print(" ");
				else
					System.out.print(cell.getPlayer().getSymbol());

				System.out.print("|");
			}

			System.out.println();
		}
	}

	public Board initiateBoard(int rowNum, int colNum) {

		Board board = new Board(rowNum, colNum);

		List<List<Cell>> cellsList = board.getCellsList();

		for (int i = 0; i < rowNum; i++) {
			List<Cell> cells = new ArrayList<>();

			for (int j = 0; j < colNum; j++) {
				cells.add(new Cell(i, j));
			}
			cellsList.add(cells);
		}
		return board;
	}

}
