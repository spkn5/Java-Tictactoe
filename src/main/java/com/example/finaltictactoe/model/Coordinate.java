package com.example.finaltictactoe.model;

/**
 * 
 * @author
 * 
 * The class to hold i and j
 *
 */
public class Coordinate {
	
	private int i;
	private int j;
	
	public Coordinate(int i, int j) {
		this.i = i;
		this.j = j;
	}
		

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}
	
	public boolean equals(Object other) {
		if( other == null )
			return false;
		if( other instanceof Coordinate ) {
			Coordinate that = (Coordinate)other;
			return this.i == that.i && this.j == that.j;
		}
		else {
			return false;
		}
	}
	
	public String toString() {
		return "(" + i + "," + j + ")";
	}
}
