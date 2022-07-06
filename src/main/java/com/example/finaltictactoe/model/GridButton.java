package com.example.finaltictactoe.model;

import javafx.scene.control.Button;

public class GridButton extends Button {

	private int i;
	private int j;
	private char symbol;
	
	public GridButton(int i,int j, char symbol) {
		this.i = i;
		this.j = j;
		setSymbol(symbol);
	}
	
	public char getSymbol() {
		return symbol;
	}

	public int getI() {
		return i;
	}

	public int getJ() {
		return j;
	}
	
	public void setSymbol(char symbol) {
		this.symbol = symbol;
		setText("   " + symbol + "   ");
	}
}
