package com.example.finaltictactoe.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.scene.control.Alert.AlertType;
import com.example.finaltictactoe.model.ComputerPlayer;
import com.example.finaltictactoe. model.Coordinate;
import com.example.finaltictactoe. model.GridButton;
import com.example.finaltictactoe. model.HumanPlayer;
import com.example.finaltictactoe. model.Player;
import com.example.finaltictactoe. model.SOS;
import com.example.finaltictactoe. viewer.SOSGameView;

public class SOSGame {
	
	public static final char PLAYER_S = 'S';
	public static final char PLAYER_O = 'O';
	public static final char EMPTY = ' ';
	
	// the type of the game
	public static final int SIMPLE_GAME = 1;
	public static final int GENERAL_GAME = 2;
	
	// the colors
	public static final String RED_COLOR = "Red";
	public static final String BLUE_COLOR = "Blue";
	
	// the directions of SOS
	public static final int HORIZONTAL = 1;
	public static final int VERTICAL = 2;
	public static final int LEFT_DIAGONAL = 3;
	public static final int RIGHT_DIAGONAL = 4;
	
	public static final boolean HUMAN_PLAYER = true;
	public static final boolean COMPUTER_PLAYER = false;
	
	// the random number generator
	public static final Random RANDOM = new Random();
	
	// the list of the coordinates with an SOS
	private List<SOS> allSOS;
		
	// the size of the gird
	private int n;
	
	// this has the board
	private char[][] grid;
	
	// private Player humanPlayer;
	// private Player computerPlayer;
	
	private Player player1;
	private Player player2;
	
	private boolean sosFormed;
	
	private Player currentPlayer;
	
	// the type of the game being played
	private int gameType;
	
	private SOSGameView view;
	
	
	// the constructor
//	public SOSGame(int gameType, 
//			int n, 
//			String humanPlayerColor,
//			String computerPlayerColor,
//			char humanPlayerSymbol, 
//			char computerPlayerSymbol,
//			SOSGameView view) {
//		
//		
//		// setup the game 
////		this.gameType = gameType;
//		this.n = n;
//		this.grid = new char[n][n];
//		allSOS = new ArrayList<>();
//		clearGrid();
//		this.view = view;
////		humanPlayer = new HumanPlayer(humanPlayerColor, humanPlayerSymbol);
////		computerPlayer = new HumanPlayer(computerPlayerColor, computerPlayerSymbol);
////		allSOS = new ArrayList<>();
//		setupNewGame(gameType, humanPlayerColor, computerPlayerColor, view);
//	}
	
//	public SOSGame(int gameType, 
//			int n, 
//			String player1Color,
//			String player2Color,
//			char player1Symbol, 
//			char player2Symbol,
//			boolean player1Type,
//			boolean player2Type,
//			SOSGameView view) {
//		
//		
//		// setup the game 
////		this.gameType = gameType;
//		this.n = n;
//		this.grid = new char[n][n];
//		allSOS = new ArrayList<>();
//		clearGrid();
//		this.view = view;
////		humanPlayer = new HumanPlayer(humanPlayerColor, humanPlayerSymbol);
////		computerPlayer = new HumanPlayer(computerPlayerColor, computerPlayerSymbol);
////		allSOS = new ArrayList<>();
////		setupNewGame(gameType, humanPlayerColor, computerPlayerColor, view);
//		this.gameType = gameType;
//		if( player1Type == HUMAN_PLAYER )
//			player1 = new HumanPlayer(player1Color);
//		else 
//			player1 = new ComputerPlayer(player1Color);
//	
//		if( player2Type == HUMAN_PLAYER )
//			player2 = new HumanPlayer(player2Color);
//		else 
//			player2 = new ComputerPlayer(player2Color);
//		currentPlayer = player1;
//		
//		// TODO: Fix this
//		view.updateView(grid, currentPlayer, allSOS);
//	}
	
	public SOSGame(int gameType, 
			int n, 
			String player1Color,
			String player2Color,
			boolean player1Type,
			boolean player2Type,
			SOSGameView view) {
		this.n = n;
		this.grid = new char[n][n];
		allSOS = new ArrayList<>();
		clearGrid();
		
		// TODO: Fix this
		setupNewGame(gameType, player1Color, player2Color, player1Type, player2Type, view);
	}
	
	
	public SOSGame(int n) {
		this.n = n;
		this.grid = new char[n][n];
		allSOS = new ArrayList<>();
		clearGrid();
		sosFormed = false;
	}
	
	public void setupNewGame(int gameType, String player1Color, String player2Color, boolean player1Type, boolean player2Type, SOSGameView view) {
		
		this.gameType = gameType;
		this.view = view;

		if( player1Type == HUMAN_PLAYER )
			player1 = new HumanPlayer(player1Color);
		else 
			player1 = new ComputerPlayer(player1Color);
	
		if( player2Type == HUMAN_PLAYER )
			player2 = new HumanPlayer(player2Color);
		else 
			player2 = new ComputerPlayer(player2Color);
		currentPlayer = player1;
		sosFormed = false;
		// view.updateView(grid, currentPlayer, allSOS);
		// view.updateView(grid, currentPlayer, allSOS);
	}
//	
//	public void setupNewGame(int gameType, String humanPlayerColor, String computerPlayerColor) {
//		
//		this.gameType = gameType;
//		humanPlayer = new HumanPlayer(humanPlayerColor);
//		computerPlayer = new ComputerPlayer(computerPlayerColor);
//		currentPlayer = humanPlayer;
//		view.updateView(grid, currentPlayer, allSOS);
//	}
	
	public void setView(SOSGameView view) {
		this.view = view;
	}
	
	
	// check if gameOver
	private boolean isGameOver() {
		
//		SOS sos = checkSOSFormed();
//		if( sos!=null )
//			allSOS.add(sos);
		
		// if this is a simple game 
		if( gameType == SIMPLE_GAME ) {
			// return sos != null;
			return sosFormed;
		}
		else {
			return !gridHasEmptySpace();
		}
		
	}
	
	// get the winner with more score in general game 
//	private Player getGeneralGameWinner() {
//		if( humanPlayer.getScore() > computerPlayer.getScore() )
//			return humanPlayer;
//		else if( humanPlayer.getScore() < computerPlayer.getScore() ) {
//			return computerPlayer;
//		}
//		else 
//			return null;
//	}
	
	private Player getGeneralGameWinner() {
		if( player1.getScore() > player2.getScore() )
			return player1;
		else if( player1.getScore() < player2.getScore() ) {
			return player2;
		}
		else 
			return null;
	}
	
	// get winner in simple game
//	private Player getSimpleGameWinner() {
//		return currentPlayer==humanPlayer? computerPlayer: humanPlayer;
//	}
	
	private Player getSimpleGameWinner() {
		// return currentPlayer==humanPlayer? computerPlayer: humanPlayer;
		return currentPlayer;
	}
	
	// the method to get winner
	private Player getWinner() {
		if( gameType == SIMPLE_GAME ) {
			return getSimpleGameWinner();
		}
		else {
			return getGeneralGameWinner();
		}
	}
	
	// the method to declare winner
	private void declareWinner() {
		view.displayWinner(getWinner());
	}
	
	// the method to check if there is a blank space left on the grid
	private boolean gridHasEmptySpace() {
		for( int i=0; i<n; i++ ) {
			for( int j=0; j<n; j++ ) {
				if( grid[i][j] == EMPTY )
					return true;
			}
		}
		return false;
	}
	
	// check if an SOS from this coordinate and in the given direction
//	private boolean containsSOS(Coordinate coordinate, int direction) {
//		for( SOS sos : allSOS ) {
//			if( sos.getCoordinate().equals(coordinate) && sos.getDirection() == direction )
//				return true;
//		}
//		return false;
//	}
	
	private boolean containsSOS(Coordinate coordinate) {
		for( SOS sos : allSOS ) {
			if( sos.getCoordinate().equals(coordinate) )
				return true;
		}
		return false;
	}

	
	// the method to check sos formed
	private SOS checkSOSFormed() {
		// iterate the 2D grid
		for( int i=0; i<n; i++ ) {
			for( int j=0; j<n; j++ ) {
				
				if( containsSOS(new Coordinate(i,j)))
					continue;
				
				// System.out.println("Checking SOS, current player: " + currentPlayer.getName());
				Player playerPrevTurn = getOtherPlayer(currentPlayer);
				
				// check horizontal - forward
				if( j+1<n && j+2<n ) {
					if( grid[i][j]==PLAYER_S && grid[i][j+1]==PLAYER_O && grid[i][j+2]==PLAYER_S) {
						
						playerPrevTurn.addScore(1);
						// return new SOS(new Coordinate(i, j), HORIZONTAL);
						return new SOS(new Coordinate(i, j), HORIZONTAL, playerPrevTurn);
					}
				}
				
				// check horizontal - backward - redundant?
//				if( j-1>=0 && j-2>=0 ) {
//					if( grid[i][j]==PLAYER_S && grid[i][j-1]==PLAYER_O && grid[i][j-2]==PLAYER_S) {
//						return true;
//					}
//				}
				
				// check vertical - top to bottom
				if( i+1<n && i+2<n ) {
					if( grid[i][j]==PLAYER_S && grid[i+1][j]==PLAYER_O && grid[i+2][j]==PLAYER_S) {
						// return new SOS(new Coordinate(i, j), VERTICAL);
						playerPrevTurn.addScore(1);
						return new SOS(new Coordinate(i, j), VERTICAL, playerPrevTurn);
					}
				}
				
				// check vertical - bottom to top - redundant?
//				if( i-1>=0 && i-2>=0 ) {
//					if( grid[i][j]==PLAYER_S && grid[i-1][j]==PLAYER_O && grid[i-2][j]==PLAYER_S) {
//						return true;
//					}
//				}
				
				// check left diagonal 
				int a=i;
				int b=j;
				if( a+1<n && a+2<n && b+1<n && b+2<n) {
					if( grid[a][b]==PLAYER_S && grid[a+1][b+1]==PLAYER_O && grid[a+2][b+2]==PLAYER_S) {
						// return new SOS(new Coordinate(i, j), LEFT_DIAGONAL);
						playerPrevTurn.addScore(1);
						return new SOS(new Coordinate(i, j), LEFT_DIAGONAL, playerPrevTurn);
					}
				}
				
				// check right diagonal 
				a=i;
				b=j;
				if( a-1>=0 && a-2>=0 && b+1<n && b+2<n) {
					if( grid[a][b]==PLAYER_S && grid[a-1][b+1]==PLAYER_O && grid[a-2][b+2]==PLAYER_S) {
						// return new SOS(new Coordinate(i, j), RIGHT_DIAGONAL);
						playerPrevTurn.addScore(1);
						return new SOS(new Coordinate(i, j), RIGHT_DIAGONAL, playerPrevTurn);
					}
				}
			}
		}
		return null;
	}
	
	
	// the method to check if and SOS formed
//	private boolean isFirstSOSFormed() {
//		return checkSOSFormed() != null;
//	}

	// takes the view 
//	public void play() {
//		
//		// get the current player
//		currentPlayer = humanPlayer;
//		
//		while( !isGameOver() ) {
//			
//			// make move 
//			
//			// change turns if game not over
//			if( !isGameOver() ) {
//				currentPlayer = currentPlayer == humanPlayer? computerPlayer : humanPlayer;
//			}
//		}
//		
//		// declare winner
//		declareWinner();
//	}
	
//	public void buttonClicked(GridButton button, char symbol) {
//		grid[button.getI()][button.getJ()] = symbol;
//		
//		// change turn
//		currentPlayer = currentPlayer == player1? player2: player1;
//		// update view
//		view.updateView(grid,currentPlayer,allSOS);
//					
//		// take turn
//		takeTurn();
//	}
	
	public void buttonClicked(GridButton button) {
		grid[button.getI()][button.getJ()] = view.getSymbol(currentPlayer.getColor());
		// change turn
		currentPlayer = getOtherPlayer(currentPlayer);
		// update sos
		SOS sos = checkSOSFormed();
		if( sos!=null ) {
			allSOS.add(sos);
			sosFormed = true;
		}
		
		// update view
		view.updateView(grid,currentPlayer,allSOS);
					
		// take turn
		takeTurn();
	}
	
	public int getN() {
		return n;
	}
	
//	public void play() {
//		// in play
//		// System.out.println("in play");
//		
//		// while( !isGameOver() ) {
//			// show current turn
//		// view.showCurrentTurn(currentPlayer);
//		
//		// if the current player is a computer, make move
//		if( currentPlayer instanceof ComputerPlayer ) {
//			// make move
//			Coordinate coordinate = currentPlayer.makeMove(this);
//			
//			// chose symbol
//			char symbol = RANDOM.nextInt()%2 == 0? PLAYER_O: PLAYER_S;
//			// set the grid
//			grid[coordinate.getI()][coordinate.getJ()] = symbol;
//			currentPlayer = currentPlayer == player1? player2: player1;
//			
//			// update view
//			
//		}
//		// else take input from the user
//		else {
//			
//		}
//		// }
//				
//		// 
//	}
//	
	public void takeTurn() {
		// view.updatSOSs(grid,allSOS);
		if( isGameOver() ) {
			System.out.println("Game over");
			Player winner = currentPlayer == player1? player2: player1;
			// show alert 
			view.showAlert(AlertType.CONFIRMATION, "SOS Game Result", winner.getName() + "(" + winner.getColor() + ") wins!");
			// reset the game
			view.resetGame();
			
			
			sosFormed = false;
			allSOS = new ArrayList<>();
			clearGrid();
			
			view.updatSOSs(grid,allSOS);
			
			return;
		}
		// view.showCurrentTurn(currentPlayer);
		// view.showAlert(AlertType.CONFIRMATION, "SOS Game", "Current turn: " + currentPlayer.getName() + "(" + currentPlayer.getColor() + ")");
		view.showTurn(currentPlayer);
		
		// if the current player is a computer, make move
		if( currentPlayer instanceof ComputerPlayer ) {
			// make move
			Coordinate coordinate = currentPlayer.makeMove(this);
			
			// chose symbol
			char symbol = RANDOM.nextInt()%2 == 0? PLAYER_O: PLAYER_S;
			// set the grid
			grid[coordinate.getI()][coordinate.getJ()] = symbol;
			currentPlayer = getOtherPlayer(currentPlayer);
			
			// update sos
			SOS sos = checkSOSFormed();
			if( sos!=null ) {
				allSOS.add(sos);
				sosFormed = true;
			}
			
			// update view
			view.updateView(grid,currentPlayer,allSOS);
			takeTurn();
		}
		// else take input from the user
//		else {
//			
//		}
		// change turn
		// currentPlayer = currentPlayer == player1? player2: player1;
	}
	
//	private void makeComputerPlayerMove() {
//		Coordinate coordinate = computerPlayer.makeMove(this);
//		
//		// chose symbol
//		char symbol = RANDOM.nextInt()%2 == 0? PLAYER_O: PLAYER_S;
//		// set the grid
//		grid[coordinate.getI()][coordinate.getJ()] = symbol;
//		
//		// check game over
//		if( isGameOver() ) {
//			// declare winner
//			declareWinner();
//		}
//		// change turn
//		// currentPlayer = humanPlayer;
//		// System.out.println("Turn changed to human player");
//		view.updateView(grid, currentPlayer, allSOS);
//	}
	
//	private void makeComputerPlayerMove(Player player) {
//		Coordinate coordinate = player.makeMove(this);
//		
//		// chose symbol
//		char symbol = RANDOM.nextInt()%2 == 0? PLAYER_O: PLAYER_S;
//		// set the grid
//		grid[coordinate.getI()][coordinate.getJ()] = symbol;
//		
//		// check game over
//		if( isGameOver() ) {
//			// declare winner
//			declareWinner();
//		}
//		// change turn
//		// currentPlayer = humanPlayer;
//		// System.out.println("Turn changed to human player");
//		view.updateView(grid, currentPlayer, allSOS);
//	}
//	
//	public void makeHumanPlayerMove(GridButton gridButton) {
//		Coordinate coordinate = humanPlayer.makeMove(gridButton);
//		
//		// update the grid
//		char symbol = gridButton.getSymbol();
//		grid[coordinate.getI()][coordinate.getJ()] = symbol;
//		currentPlayer = humanPlayer;
//		
//		// check game over
//		if( isGameOver() ) {
//			// declare winner
//			declareWinner();
//		}
//		// else make computer move
//		else {
//			currentPlayer = computerPlayer;
//			makeComputerPlayerMove();
//		}
//		// currentPlayer = computerPlayer;
//		// System.out.println("Turn changed to computer player");
//		view.updateView(grid, currentPlayer, allSOS);
//	}
	
//	private void makeHumanPlayerMove(GridButton gridButton, Player player) {
//		Coordinate coordinate = player.makeMove(gridButton);
//		
//		// update the grid
//		char symbol = gridButton.getSymbol();
//		grid[coordinate.getI()][coordinate.getJ()] = symbol;
//		currentPlayer = player;
//		view.updateView(grid, currentPlayer, allSOS);
//		
//		// check game over
//		if( isGameOver() ) {
//			// declare winner
//			declareWinner();
//		}
//		// else make computer move
//		else {
//			// change current player
//			currentPlayer = player == player1? player2:player1;
//			makeComputerPlayerMove(currentPlayer);
//		}
//		// currentPlayer = computerPlayer;
//		// System.out.println("Turn changed to computer player");
//		// view.updateView(grid, currentPlayer, allSOS);
//	}
//	
//	private void makeHumanPlayerMove(GridButton gridButton) {
//		Coordinate coordinate = currentPlayer.makeMove(gridButton);
//		
//		// update the grid
//		char symbol = gridButton.getSymbol();
//		grid[coordinate.getI()][coordinate.getJ()] = symbol;
//		// currentPlayer = player;
//		view.updateView(grid, currentPlayer, allSOS);
//		
//		// check game over
//		if( isGameOver() ) {
//			// declare winner
//			declareWinner();
//		}
//		// else make computer move
//		else {
//			// change current player
//			currentPlayer = currentPlayer == player1? player2:player1;
//			makeComputerPlayerMove(currentPlayer);
//		}
//		// currentPlayer = computerPlayer;
//		// System.out.println("Turn changed to computer player");
//		// view.updateView(grid, currentPlayer, allSOS);
//	}
	
	public char[][] getGrid() {
		return grid;
	}
	
	public void clearGrid() {
		for( int i=0; i<n; i++ ) {
			for( int j=0; j<n; j++ ) {
				grid[i][j] = EMPTY;
			}
		}
		allSOS.clear();
		
	}


	public Player getPlayer1() {
		return player1;
	}


	public Player getPlayer2() {
		return player2;
	}


	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	public Player getOtherPlayer(Player player) {
		return player == player1? player2:player1;
	}
}
