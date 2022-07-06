package com.example.finaltictactoe.model;

public abstract class Player {

	// the color of this player 
	private String color;
	
	// the score of this player
	private int score;
	
	public Player(String color) {
		this.color = color;
		this.score = 0;
	}
	
	// the method to make a move
	public abstract Coordinate makeMove(Object sender);
	public abstract String getName();

	public String getColor() {
		return color;
	}

	public int getScore() {
		return score;
	}
	
	public void addScore(int increment) {
		score += increment;
	}
	
	
	
}
