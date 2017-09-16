package org.broadhurst.natalie.projects.scrabble;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Tile {
	private static final Map<Character, Integer> points = initialize();
	private char letter;
	private char display;

	public Tile() {
		this.letter = '\0';
		this.display = '\0';
	}

	public Tile(char letter) {
		this.letter = Character.toUpperCase(letter);
		this.display = Character.toUpperCase(letter);
	}
	
	public Tile(char letter, char display) {
		this.letter = Character.toUpperCase(letter);
		this.display = Character.toUpperCase(display);
	}
	
	public char getLetter() {
		return this.letter;
	}

	public int getPoints() {
		return points.get(this.letter);
	}
	
	public void setDisplay(char display) {
		if (this.letter != '?') {
			throw new IllegalArgumentException("can't change the display for a non-blank tile");
		} else if (!Character.isLetter(display)) {
			throw new IllegalArgumentException("Error: '" + display + "' is not a valid letter");
		}
		this.display = Character.toUpperCase(display);
	}
	
	public static List<Tile> getTilesForLetters(CharSequence letters) {
		List<Tile> tiles = new ArrayList<>();
		for (int i = 0; i < letters.length(); i++) {
			tiles.add(new Tile(letters.charAt(i)));
		}
		return tiles;
	}

	private static Map<Character, Integer> initialize() {
		Map<Character, Integer> points = new HashMap<>();
		points.put('A', 1);
		points.put('B', 3);
		points.put('C', 3);
		points.put('D', 2);
		points.put('E', 1);
		points.put('F', 4);
		points.put('G', 2);
		points.put('H', 4);
		points.put('I', 1);
		points.put('J', 8);
		points.put('K', 5);
		points.put('L', 1);
		points.put('M', 3);
		points.put('N', 1);
		points.put('O', 1);
		points.put('P', 3);
		points.put('Q', 10);
		points.put('R', 1);
		points.put('S', 1);
		points.put('T', 1);
		points.put('U', 1);
		points.put('V', 4);
		points.put('W', 4);
		points.put('X', 8);
		points.put('Y', 4);
		points.put('Z', 10);
		points.put('?', 0);
		return points;
	}
	
	@Override
	public String toString() {
		return "|\u001b[47;1m " + String.valueOf(this.display) + "  \u001b[0m";
	}
}
