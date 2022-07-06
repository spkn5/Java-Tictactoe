package com.example.finaltictactoe.model;

public class SOS {
	
	// the coordinate 
	private Coordinate coordinate;
	
	// the direction
	private int direction;
	
	// the owner
	private Player owner;
	
	public SOS(Coordinate coordinate, int direction) {
		super();
		this.coordinate = coordinate;
		this.direction = direction;
	}
	
	public SOS(Coordinate coordinate, int direction, Player owner) {
		super();
		this.coordinate = coordinate;
		this.direction = direction;
		this.owner = owner;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public int getDirection() {
		return direction;
	}

	public Player getOwner() {
		return owner;
	}
}
