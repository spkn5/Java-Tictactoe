package com.example.finaltictactoe.model;

public class HumanPlayer extends Player {
	
	// takes in the coordinates clicked 

	public HumanPlayer(String color) {
		super(color);
	}

	@Override
	public Coordinate makeMove(Object sender) {
		// get the button
		GridButton buttonClicked = (GridButton)sender;
		return new Coordinate(buttonClicked.getI(), buttonClicked.getJ());
	}

	@Override
	public String getName() {
		return "Human";
	}
}
