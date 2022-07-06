package com.example.finaltictactoe. model;

import com.example.finaltictactoe.controller.SOSGame;
import com.example.finaltictactoe.model.Coordinate;
import com.example.finaltictactoe.model.Player;

public class ComputerPlayer extends Player {

	public ComputerPlayer(String color) {
		super(color);
	}

	@Override
	public Coordinate makeMove(Object sender) {
		
		SOSGame game = (SOSGame)sender;
		int n = game.getN();
		
		// look for the first non empty space
		int i = SOSGame.RANDOM.nextInt(n);
		int j = SOSGame.RANDOM.nextInt(n);
		char[][] grid = game.getGrid();
		
		while( grid[i][j] != SOSGame.EMPTY ) {
			i = SOSGame.RANDOM.nextInt(n);
			j = SOSGame.RANDOM.nextInt(n);
		}
		
		return new Coordinate(i, j);
	}

	@Override
	public String getName() {
		return "Computer";
	}

}
