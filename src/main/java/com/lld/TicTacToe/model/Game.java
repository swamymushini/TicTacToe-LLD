package com.lld.TicTacToe.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.lld.TicTacToe.service.WinningStrategy;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Game {

	private List<Player> players;
	private Player winner;
	private List<Move> moves = new LinkedList<>();
	private GameState gameState;
	List<WinningStrategy> winningStrategies;
	private int currentPlayerIndex = 0;
	private Board board;

	public static Builder getBuilder() {
		return new Builder();
	}

	public void nextPlayerTurn() {
		currentPlayerIndex =  (currentPlayerIndex + 1) % 2;
	}

	public Player getCurrentPlayer() {
		return players.get(currentPlayerIndex);
	}

	public void addMove(Move move) {
		moves.add(move);
	}

	public Game(List<Player> players, GameState gameState, List<WinningStrategy> winningStrategies) {
		super();
		this.players = players;
		this.gameState = gameState;
		this.winningStrategies = winningStrategies;
	}

	public static class Builder {

		private List<Player> players = new ArrayList<>();
		private GameState gameState;
		private List<WinningStrategy> winningStrategies = new ArrayList<>();

		public Game build() {
			return new Game(players, gameState, winningStrategies);
		}

		public List<Player> getPlayers() {
			return players;
		}

		public Builder addPlayer(Player player) {
			players.add(player);
			return this;
		}

		public Builder addWinningStrategy(WinningStrategy strategy) {
			winningStrategies.add(strategy);
			return this;
		}

		public GameState getGameState() {
			return gameState;
		}

		public List<WinningStrategy> getWinningStrategies() {
			return winningStrategies;
		}

		public Builder setPlayers(List<Player> players) {
			this.players = players;
			return this;
		}

		public Builder setGameState(GameState gameState) {
			this.gameState = gameState;
			return this;
		}

		public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
			this.winningStrategies = winningStrategies;
			return this;
		}

	}
}
