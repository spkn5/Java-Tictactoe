package com.example.finaltictactoe.viewer;

import java.util.List;
import java.util.Optional;

import com.example.finaltictactoe.controller.SOSGame;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import com.example.finaltictactoe.model.ComputerPlayer;
import com.example.finaltictactoe.model.Coordinate;
import com.example.finaltictactoe.model.GridButton;
import com.example.finaltictactoe.model.Player;
import com.example.finaltictactoe.model.SOS;

public class SOSGameView extends Stage {
	
	// the controls
	private RadioButton simpleGameRadioButton;
	private RadioButton generalGameRadioButton;
	
	private RadioButton bluePlayerHumanRadioButton;
	private RadioButton bluePlayerComputerRadioButton;
	private RadioButton bluePlayerRadioButtonS;
	private RadioButton bluePlayerRadioButtonO;
	
	private RadioButton redPlayerHumanRadioButton;
	private RadioButton redPlayerComputerRadioButton;
	private RadioButton redPlayerRadioButtonS;
	private RadioButton redPlayerRadioButtonO;
	
	private GridButton grid[][];
	private int n;

	private Button replayButton;
	private Button newGameButton;
	
	private CheckBox recordGameCheckBox;
	
	private Label currentTurn;
	
	private static int WIDTH = 450;
	private static int HEIGHT = 600;
	
	private SOSGame gameController;
	
	private VBox mainPane;
	// private StackPane midMidBox;
	private Group midMidBox;
	private VBox gridBox;
	
	public SOSGameView() {
		this(10);
		setScene(createScene());
	}
	
	public SOSGameView(int n) {
		super();
		this.n = n;
		WIDTH = 450 + ((n-4) * 50);
		setScene(createScene());
	}
	
	public SOSGameView(SOSGame gameController) {
		super();
		this.gameController = gameController;
		this.n = gameController.getN();
		WIDTH = 450 + ((n-4) * 50);
		setScene(createScene());
	}
	
	// the method to get the middle box
	private VBox getMidMidBox() {
		VBox gridBox = new VBox();
		gridBox.setPadding(new Insets(10, 20, 10, 20));
		// gridBox.setAlignment(Pos.CENTER);
		
		for( int i=0; i<n; i++) {
			HBox row = new HBox();
			for(int j=0; j<n; j++ ) {
				row.getChildren().add(grid[i][j]);
			}
			gridBox.getChildren().add(row);
		}
		return gridBox;
	}
	
	// add constraints so that only one human can be selected at a point
//	private void addConstraints() {
//
//		
//		// making sure only one human/computer is selected on either side
//		bluePlayerHumanRadioButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
//			@Override
//			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
//				if( redPlayerHumanRadioButton.isSelected() ) {
//					redPlayerHumanRadioButton.setSelected(false);
//					redPlayerComputerRadioButton.setSelected(true);
//					return;
//				}
//			}
//		});
//		
//		bluePlayerComputerRadioButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
//			@Override
//			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
//				if( redPlayerComputerRadioButton.isSelected() ) {
//					redPlayerComputerRadioButton.setSelected(false);
//					redPlayerHumanRadioButton.setSelected(true);
//					return;
//				}
//			}
//		});
//		
//		redPlayerHumanRadioButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
//			@Override
//			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
//				if( newValue && bluePlayerHumanRadioButton.isSelected() ) {
//					bluePlayerHumanRadioButton.setSelected(false);
//					bluePlayerComputerRadioButton.setSelected(true);
//					return;
//				}
//			}
//		});
//		
//		
//		redPlayerComputerRadioButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
//			@Override
//			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
//				if( newValue && bluePlayerComputerRadioButton.isSelected() ) {
//					bluePlayerComputerRadioButton.setSelected(false);
//					bluePlayerHumanRadioButton.setSelected(true);
//					return;
//				}
//			}
//		});
//	
//	}
	
	
	// the method to start a new game
	private void newGameButtonOnClick(ActionEvent event) {
		int gameType = generalGameRadioButton.isSelected()? SOSGame.GENERAL_GAME: SOSGame.SIMPLE_GAME;
//		char humanPlayerSymbol = 0;
//		String humanPlayerColor = "";
//		if( bluePlayerRadioButtonO.isSelected() ) {
//			
//		}
//		gameController.setupGame(gen, Player2Color, Player1Color, 0, 0);
		boolean player1Type;
		boolean player2Type;
		
//		String humanPlayerColor = "";
//		String computerPlayerColor = "";
		
		if( bluePlayerHumanRadioButton.isSelected() ) {
			player1Type = SOSGame.HUMAN_PLAYER;
		}
		else {
			player1Type = SOSGame.COMPUTER_PLAYER;
		}
		
		if( redPlayerHumanRadioButton.isSelected() ) {
			player2Type = SOSGame.HUMAN_PLAYER;
		}
		else {
			player2Type = SOSGame.COMPUTER_PLAYER;
		}
		
		String player1Color = SOSGame.BLUE_COLOR;
		String player2Color = SOSGame.RED_COLOR;
		
		// create a new game
		// gameController.setupNewGame(gameType, humanPlayerColor, computerPlayerColor);
		gameController.setupNewGame(gameType, player1Color, player2Color, player1Type, player2Type, this);
		
		
		// freeze the controls to change human and computer colors
		bluePlayerHumanRadioButton.setDisable(true);
		bluePlayerComputerRadioButton.setDisable(true);
		redPlayerHumanRadioButton.setDisable(true);
		redPlayerComputerRadioButton.setDisable(true);
		
		// freeze buttons to select the type of game
		simpleGameRadioButton.setDisable(true);
		generalGameRadioButton.setDisable(true);
		
		// freeze buttons to check the button
		recordGameCheckBox.setDisable(true);
		
		// enable the grid buttons
		for( int i=0; i<n; i++ ) {
			for( int j=0; j<n; j++ ) {
				grid[i][j].setSymbol(SOSGame.EMPTY);
				grid[i][j].setDisable(false);
			}
		}
		
		// show message
//		Alert startGameAlert = new Alert(AlertType.INFORMATION);
//		startGameAlert.setTitle("Start SOS Game");
//		startGameAlert.setContentText("Human Player goes first");
//		Optional<ButtonType> response = startGameAlert.showAndWait();
//		if( response.isPresent() ) {
//			// start game
//			
//		}
//		else {
//			
//		}
		
		// remove all nodes from the mid mid
		midMidBox.getChildren().clear();
		midMidBox.getChildren().add(gridBox);
		gameController.clearGrid();
		currentTurn.setText("Current Turn:\n");
		
		// take turn
		gameController.takeTurn();
		
	}
	
	// the method to set the game default selections
	private void setGameDefaultSettings() {
		// set default selections - blue is human and red is computer
		bluePlayerHumanRadioButton.setSelected(true);
		redPlayerComputerRadioButton.setSelected(true);
		
		// human is S and Computer is O
		bluePlayerRadioButtonO.setSelected(true);
		redPlayerRadioButtonS.setSelected(true);
		
		// set simple game to selected
		simpleGameRadioButton.setSelected(true);
		
		// disable all buttons
		for( int i=0; i<n; i++ ) {
			for( int j=0; j<n; j++ ) {
				grid[i][j].setDisable(true);
				grid[i][j].setSymbol(SOSGame.EMPTY);
			}
		}
			
		// enable the radio buttons
		
		// the controls to change human and computer colors
		bluePlayerHumanRadioButton.setDisable(false);
		bluePlayerComputerRadioButton.setDisable(false);
		redPlayerHumanRadioButton.setDisable(false);
		redPlayerComputerRadioButton.setDisable(false);
		
		// buttons to select the type of game
		simpleGameRadioButton.setDisable(false);
		generalGameRadioButton.setDisable(false);
		
		currentTurn.setText("Current Turn:\n");
		
		// buttons to check the button
		recordGameCheckBox.setDisable(false);
	}
	
	public void resetGame() {
		// setGameDefaultSettings();
		newGameButtonOnClick(null);
		setGameDefaultSettings();
	}
	
//	private void setNewButtonOnClick(ActionEvent event) {
//		setGameDefaultSettings();
//	}
//	
	// the method to add listener to button
	private void setGridButtonOnClick(ActionEvent event) {
		// if this 
		
		// print
		// System.out.println("Click");
		
		// disable this button
		GridButton source = (GridButton)event.getSource();
		source.setDisable(true);
		char symbol = SOSGame.EMPTY;
		if( redPlayerHumanRadioButton.isSelected() ) {
			symbol = redPlayerRadioButtonO.isSelected()? SOSGame.PLAYER_O:SOSGame.PLAYER_S;
		}
		else {
			symbol = bluePlayerRadioButtonO.isSelected()? SOSGame.PLAYER_O:SOSGame.PLAYER_S;
		}
		source.setSymbol(symbol);
		// change text
		
		// gameController.makeHumanPlayerMove(source);
		// gameController.buttonClicked(source, symbol);
		gameController.buttonClicked(source);
	}
	
	public char getSymbol(String color) {
		if( color.equals(SOSGame.BLUE_COLOR) ) {
			if( bluePlayerRadioButtonO.isSelected() )
				return SOSGame.PLAYER_O;
			else 
				return SOSGame.PLAYER_S;
		}
		else {
			if( redPlayerRadioButtonO.isSelected() )
				return SOSGame.PLAYER_O;
			else 
				return SOSGame.PLAYER_S;
		}
	}
	
	// the method to set button controllers
	private void setButtonControllers() {
		// add controller to the new game button 
		newGameButton.setOnAction(this::newGameButtonOnClick);
				
		// set controllers for the grid buttons
		for( int i=0; i<n; i++ ) {
			for( int j=0; j<n; j++ ) {
				grid[i][j].setOnAction(this::setGridButtonOnClick);
			}
		}
		
		replayButton.setOnAction(this::newGameButtonOnClick);
	}
	
	
	// the method to create scene
	private Scene createScene() {
		
		// create the main VBox
		mainPane = new VBox(5);
		
		// create an HBox to hold the top part
		HBox topBox = new HBox(10);
		topBox.setPadding(new Insets(10, 10, 10, WIDTH/2 - 100));
		topBox.getChildren().add(new Label("SOS"));
		simpleGameRadioButton = new RadioButton("Simple Game");
		generalGameRadioButton = new RadioButton("General Game");
		topBox.getChildren().add(simpleGameRadioButton);
		topBox.getChildren().add(generalGameRadioButton);
		ToggleGroup groupGameType = new ToggleGroup();
		groupGameType.getToggles().add(simpleGameRadioButton);
		groupGameType.getToggles().add(generalGameRadioButton);
		
		// create the HBox to hold the middle pane
		// HBox middleBox = new HBox(10);
		BorderPane middleBox = new BorderPane();
		// the first VBox
		VBox midLeftBox = new VBox(0);
		midLeftBox.setPadding(new Insets(10,0,0,10));
		midLeftBox.getChildren().add(new Label("Blue Player"));
		midLeftBox.getChildren().add(new Label("            "));
		bluePlayerHumanRadioButton = new RadioButton("Human");
		bluePlayerComputerRadioButton = new RadioButton("Computer");
		bluePlayerRadioButtonS = new RadioButton("S");
		bluePlayerRadioButtonO = new RadioButton("O");
		
		ToggleGroup groupBluePlayer = new ToggleGroup();
		groupBluePlayer.getToggles().add(bluePlayerHumanRadioButton);
		groupBluePlayer.getToggles().add(bluePlayerComputerRadioButton);
		
		ToggleGroup groupBluePlayerSymbol = new ToggleGroup();
		groupBluePlayerSymbol.getToggles().add(bluePlayerRadioButtonS);
		groupBluePlayerSymbol.getToggles().add(bluePlayerRadioButtonO);
		
		
		bluePlayerRadioButtonS.setPadding(new Insets(5, 0, 5, 50));
		bluePlayerRadioButtonO.setPadding(new Insets(5, 0, 5, 50));
		midLeftBox.getChildren().add(bluePlayerHumanRadioButton);
		midLeftBox.getChildren().add(bluePlayerRadioButtonS);
		midLeftBox.getChildren().add(bluePlayerRadioButtonO);
		midLeftBox.getChildren().add(bluePlayerComputerRadioButton);
		// middleBox.getChildren().add(midLeftBox);
		// middleBox.getChildren().add(BorderPane., midLeftBox);
		
		
		// the middle box
		grid = new GridButton[n][n];
		for( int i=0; i<n; i++ ) {
			for( int j=0; j<n; j++ ) {
				grid[i][j] = new GridButton(i,j,SOSGame.EMPTY);
				grid[i][j].setStyle("-fx-border-color: black;");
				grid[i][j].setPrefWidth(50);
				grid[i][j].setPrefHeight(50);
				// grid[i][j].setOnAction(this::setGridButtonOnClick);
			}
		}
		gridBox = getMidMidBox();
		// Pane midMidBox = getMidMidBox();
		// StackPane midMidBox = new StackPane();
		// midMidBox = new StackPane();
		midMidBox = new Group();
		midMidBox.getChildren().add(gridBox);
		// midMidBox.setAlignment(Pos.CENTER);
		BorderPane.setAlignment(midMidBox, Pos.CENTER);
		// middleBox.getChildren().add(midMidBox);
		
		
		// the third VBox
		VBox midRightBox = new VBox(0);
		midRightBox.setPadding(new Insets(10,20,0,10));
		midRightBox.getChildren().add(new Label("Red Player"));
		midRightBox.getChildren().add(new Label("            "));
		redPlayerHumanRadioButton = new RadioButton("Human");
		redPlayerComputerRadioButton = new RadioButton("Computer");
		redPlayerRadioButtonS = new RadioButton("S");
		redPlayerRadioButtonO = new RadioButton("O");
		
		ToggleGroup groupRedPlayer = new ToggleGroup();
		groupRedPlayer.getToggles().add(redPlayerHumanRadioButton);
		groupRedPlayer.getToggles().add(redPlayerComputerRadioButton);
		
		ToggleGroup groupRedPlayerSymbol = new ToggleGroup();
		groupRedPlayerSymbol.getToggles().add(redPlayerRadioButtonS);
		groupRedPlayerSymbol.getToggles().add(redPlayerRadioButtonO);
		
		redPlayerRadioButtonS.setPadding(new Insets(5, 0, 5, 50));
		redPlayerRadioButtonO.setPadding(new Insets(5, 0, 5, 50));
		midRightBox.getChildren().add(redPlayerHumanRadioButton);
		midRightBox.getChildren().add(redPlayerRadioButtonS);
		midRightBox.getChildren().add(redPlayerRadioButtonO);
		midRightBox.getChildren().add(redPlayerComputerRadioButton);
		// middleBox.getChildren().add(midRightBox);
		
		
		middleBox.setLeft(midLeftBox);
		middleBox.setCenter(midMidBox);
		middleBox.setRight(midRightBox);
//		middleBox.getChildren().add(midLeftBox);
//		middleBox.getChildren().add(midMidBox);
//		middleBox.getChildren().add(midRightBox);
		
		HBox bottomBox = new HBox(50);
		bottomBox.setPadding(new Insets(10, 10, 10, 20));
		recordGameCheckBox = new CheckBox("Record Game");
		currentTurn = new Label("Current Turn: ");
		currentTurn.setStyle("-fx-border-color: black;");
		currentTurn.setPadding(new Insets(10));
		replayButton = new Button("Replay");
		newGameButton = new Button("New Game");
		bottomBox.getChildren().add(recordGameCheckBox);
		bottomBox.getChildren().add(currentTurn);
		VBox buttonBox = new VBox(3);
		buttonBox.setAlignment(Pos.CENTER_RIGHT);
		buttonBox.getChildren().add(replayButton);
		buttonBox.getChildren().add(newGameButton);
		buttonBox.setPadding(new Insets(0,0,0,40));
		bottomBox.getChildren().add(buttonBox);
		// bottomBox.getChildren().add(newGameButton);
		
		mainPane.getChildren().add(topBox);
		mainPane.getChildren().add(middleBox);
		mainPane.getChildren().add(bottomBox);
		
		// making sure only one human can be selected
//		ToggleGroup humanChoice = new ToggleGroup();
//		humanChoice.getToggles().add(bluePlayerHumanRadioButton);
//		humanChoice.getToggles().add(redPlayerHumanRadioButton);
//		// making sure only one computer can be selected
//		ToggleGroup computerChoice = new ToggleGroup();
//		computerChoice.getToggles().add(bluePlayerComputerRadioButton);
//		computerChoice.getToggles().add(redPlayerComputerRadioButton);
		// addConstraints();
		
		// set game default selections
		setGameDefaultSettings();
		
		// set button controllers
		setButtonControllers();
		
		Scene scene = new Scene(mainPane, WIDTH, HEIGHT);
		return scene;
	}
	
	public void updateView(char grid[][], Player currentPlayer, List<SOS> allSOS) {
	// public void updateView(char grid[][], List<SOS> allSOS) {
		for( int i=0; i<n; i++ ) {
			for( int j=0; j<n; j++ ) {
				// this.grid[i][j].setText("   " + grid[i][j] + "   ");
				// grid[i][j].setOnAction(this::setGridButtonOnClick);
				this.grid[i][j].setSymbol(grid[i][j]);
				if( grid[i][j] != SOSGame.EMPTY ) {
					this.grid[i][j].setDisable(true);
				}
			}
		}
		
		int offsetX = 30;
		int offsetY = 20;
		
		double boxWidth = this.grid[0][0].getWidth();
		double boxHeight = this.grid[0][0].getHeight();

		// update the marks
		for( SOS sos : allSOS ) {
			Coordinate coordinate = sos.getCoordinate();
			int direction = sos.getDirection();
			int i = coordinate.getI();
			int j = coordinate.getJ();
			if( direction == SOSGame.HORIZONTAL ) {
				
				double startX = offsetX + j*boxWidth;
				double startY = offsetY + i*boxHeight + 20;
				double endX = startX + 3*boxWidth - 20;
				double endy = startY;
			
				
				Line line = new Line(startX, startY, endX, endy);
				// System.out.println("Owner: " + sos.getOwner().getName());
				// System.out.println("Coordinate: " + sos.getCoordinate());
				// System.out.println("Color: " + sos.getOwner().getColor());
				Color color = sos.getOwner().getColor().equals(SOSGame.RED_COLOR) ? Color.RED: Color.BLUE;
				line.setStroke(color);
				line.setStrokeWidth(3);
				midMidBox.getChildren().add(line);
			}
			else if( direction == SOSGame.VERTICAL ) {
				double startX = offsetX + j*boxWidth + 20;
				double startY = offsetY + i*boxHeight;
				double endX = startX;
				double endy = startY + 3*boxHeight - 20;
				
				Line line = new Line(startX, startY, endX, endy);
				// System.out.println("Owner: " + sos.getOwner().getName());
				// System.out.println("Coordinate: " + sos.getCoordinate());
				// System.out.println("Color: " + sos.getOwner().getColor());
				Color color = sos.getOwner().getColor().equals(SOSGame.RED_COLOR) ? Color.RED: Color.BLUE;
				line.setStroke(color);
				line.setStrokeWidth(3);
				midMidBox.getChildren().add(line);
			}
			else if( direction == SOSGame.LEFT_DIAGONAL ) {
				double startX = offsetX + j*boxWidth;
				double startY = offsetY + i*boxHeight;
				double endX = startX + 3*boxWidth - 20;
				double endy = startY + 3*boxHeight - 20;
				Line line = new Line(startX, startY, endX, endy);
				// System.out.println("Owner: " + sos.getOwner().getName());
				// System.out.println("Coordinate: " + sos.getCoordinate());
				// System.out.println("Color: " + sos.getOwner().getColor());
				Color color = sos.getOwner().getColor().equals(SOSGame.RED_COLOR) ? Color.RED: Color.BLUE;
				line.setStroke(color);
				line.setStrokeWidth(3);
				midMidBox.getChildren().add(line);
			}
			else if( direction == SOSGame.RIGHT_DIAGONAL ) {
				double startX = offsetX + j*boxWidth + 10;
				double startY = offsetY + i*boxHeight + 20;
				double endX = startX + 3*boxWidth - 30;
				double endy = startY - 3*boxHeight + 30;
				Line line = new Line(startX, startY, endX, endy);
				// System.out.println("Owner: " + sos.getOwner().getName());
				// System.out.println("Coordinate: " + sos.getCoordinate());
				// System.out.println("Color: " + sos.getOwner().getColor());
				Color color = sos.getOwner().getColor().equals(SOSGame.RED_COLOR) ? Color.RED: Color.BLUE;
				line.setStroke(color);
				line.setStrokeWidth(3);
				midMidBox.getChildren().add(line);
			}
		}
		
		// update current turn
		// currentTurn.setText("Current Turn:\n" + currentPlayer.getName());
		currentTurn.setText("Current Turn:\n" + currentPlayer.getName());
	}
	
	public void updatSOSs(char grid[][], List<SOS> allSOS) {
		for( int i=0; i<n; i++ ) {
			for( int j=0; j<n; j++ ) {
				// this.grid[i][j].setText("   " + grid[i][j] + "   ");
				// grid[i][j].setOnAction(this::setGridButtonOnClick);
				this.grid[i][j].setSymbol(grid[i][j]);
				if( grid[i][j] != SOSGame.EMPTY ) {
					this.grid[i][j].setDisable(true);
				}
			}
		}
		
		int offsetX = 30;
		int offsetY = 20;
		
		double boxWidth = this.grid[0][0].getWidth();
		double boxHeight = this.grid[0][0].getHeight();

		// update the marks
		for( SOS sos : allSOS ) {
			Coordinate coordinate = sos.getCoordinate();
			int direction = sos.getDirection();
			int i = coordinate.getI();
			int j = coordinate.getJ();
			if( direction == SOSGame.HORIZONTAL ) {
				
				double startX = offsetX + j*boxWidth;
				double startY = offsetY + i*boxHeight + 20;
				double endX = startX + 3*boxWidth - 20;
				double endy = startY;
			
				
				Line line = new Line(startX, startY, endX, endy);
				// System.out.println("Owner: " + sos.getOwner().getName());
				// System.out.println("Coordinate: " + sos.getCoordinate());
				// System.out.println("Color: " + sos.getOwner().getColor());
				Color color = sos.getOwner().getColor().equals(SOSGame.RED_COLOR) ? Color.RED: Color.BLUE;
				line.setStroke(color);
				line.setStrokeWidth(3);
				midMidBox.getChildren().add(line);
			}
			else if( direction == SOSGame.VERTICAL ) {
				double startX = offsetX + j*boxWidth + 20;
				double startY = offsetY + i*boxHeight;
				double endX = startX;
				double endy = startY + 3*boxHeight - 20;
				
				Line line = new Line(startX, startY, endX, endy);
				// System.out.println("Owner: " + sos.getOwner().getName());
				// System.out.println("Coordinate: " + sos.getCoordinate());
				// System.out.println("Color: " + sos.getOwner().getColor());
				Color color = sos.getOwner().getColor().equals(SOSGame.RED_COLOR) ? Color.RED: Color.BLUE;
				line.setStroke(color);
				line.setStrokeWidth(3);
				midMidBox.getChildren().add(line);
			}
			else if( direction == SOSGame.LEFT_DIAGONAL ) {
				double startX = offsetX + j*boxWidth;
				double startY = offsetY + i*boxHeight;
				double endX = startX + 3*boxWidth - 20;
				double endy = startY + 3*boxHeight - 20;
				Line line = new Line(startX, startY, endX, endy);
				// System.out.println("Owner: " + sos.getOwner().getName());
				// System.out.println("Coordinate: " + sos.getCoordinate());
				// System.out.println("Color: " + sos.getOwner().getColor());
				Color color = sos.getOwner().getColor().equals(SOSGame.RED_COLOR) ? Color.RED: Color.BLUE;
				line.setStroke(color);
				line.setStrokeWidth(3);
				midMidBox.getChildren().add(line);
			}
			else if( direction == SOSGame.RIGHT_DIAGONAL ) {
				double startX = offsetX + j*boxWidth + 10;
				double startY = offsetY + i*boxHeight + 20;
				double endX = startX + 3*boxWidth - 30;
				double endy = startY - 3*boxHeight + 30;
				Line line = new Line(startX, startY, endX, endy);
				// System.out.println("Owner: " + sos.getOwner().getName());
				// System.out.println("Coordinate: " + sos.getCoordinate());
				// System.out.println("Color: " + sos.getOwner().getColor());
				Color color = sos.getOwner().getColor().equals(SOSGame.RED_COLOR) ? Color.RED: Color.BLUE;
				line.setStroke(color);
				line.setStrokeWidth(3);
				midMidBox.getChildren().add(line);
			}
		}
		
		// update current turn
		// currentTurn.setText("Current Turn:\n" + currentPlayer.getName());
		// currentTurn.setText("Current Turn:\n" + currentPlayer.getName());
	}
	
	public void displayWinner(Player player) {
		
		Alert showWinnerAlert = new Alert(AlertType.CONFIRMATION);
		showWinnerAlert.setTitle("Game Over!");
		
		// if winner is null - it is a tie
		if( player == null ) {
			showWinnerAlert.setContentText("Its a tie!");
		}
		else {
			showWinnerAlert.setContentText(player.getName() +  " wins!");
		}
		showWinnerAlert.showAndWait();
		// System.exit(0);
	}
	
//	public void showCurrentTurn(Player currenPlayer) {
//		Alert alert = new Alert(AlertType.CONFIRMATION);
//		alert.setTitle("SOS Game");
//		alert.setContentText("Current turn: " + currenPlayer.getName() + "(" + currenPlayer.getColor() + ")");
//		alert.showAndWait();
//	}
	
	public void showTurn(Player player) {
		if( player instanceof ComputerPlayer ) {
			showAlert(AlertType.CONFIRMATION, "SOS Game", "Current turn: " + player.getName() + "(" + player.getColor() + ")");
		}
		else 
			currentTurn.setText("Current Turn:\n" + player.getName() + "(" + player.getColor() + ")");
	}
	
	
	public void showAlert(AlertType alertType, String title, String contextString) {
		// force refresh
		getScene().getWindow().setWidth(this.getScene().getWidth() + 0.001);

		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setContentText(contextString);
		alert.showAndWait();
	}
}
