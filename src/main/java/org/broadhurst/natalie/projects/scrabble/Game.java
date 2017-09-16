package org.broadhurst.natalie.projects.scrabble;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class Game {
	private Board board = new Board();
	private Deque<Tile> bag = new ArrayDeque<>();
	
	public Game() {
		initialize();
	}
	
	public List<Tile> drawTiles(int count) {
		int drawn = 0;
		List<Tile> tiles = new ArrayList<>();
		while (drawn < count && !this.bag.isEmpty()) {
			tiles.add(this.bag.pop());
			drawn++;
		}
		return tiles;
	}
	
	public Board getBoard() {
		return this.board;
	}
	
	private void initialize() {
		List<Tile> tiles = new ArrayList<>(100);
		tiles.addAll(Tile.getTilesForLetters("EEEEEEEEEEEE"));
		tiles.addAll(Tile.getTilesForLetters("AAAAAAAAA"));
		tiles.addAll(Tile.getTilesForLetters("IIIIIIIII"));
		tiles.addAll(Tile.getTilesForLetters("OOOOOOOO"));
		tiles.addAll(Tile.getTilesForLetters("TTTTTT"));
		tiles.addAll(Tile.getTilesForLetters("RRRRRR"));
		tiles.addAll(Tile.getTilesForLetters("NNNNNN"));
		tiles.addAll(Tile.getTilesForLetters("UUUU"));
		tiles.addAll(Tile.getTilesForLetters("SSSS"));
		tiles.addAll(Tile.getTilesForLetters("LLLL"));
		tiles.addAll(Tile.getTilesForLetters("DDDD"));
		tiles.addAll(Tile.getTilesForLetters("GGG"));
		tiles.addAll(Tile.getTilesForLetters("??"));
		tiles.addAll(Tile.getTilesForLetters("PP"));
		tiles.addAll(Tile.getTilesForLetters("MM"));
		tiles.addAll(Tile.getTilesForLetters("CC"));
		tiles.addAll(Tile.getTilesForLetters("BB"));
		tiles.addAll(Tile.getTilesForLetters("YY"));
		tiles.addAll(Tile.getTilesForLetters("WW"));
		tiles.addAll(Tile.getTilesForLetters("VV"));
		tiles.addAll(Tile.getTilesForLetters("HH"));
		tiles.addAll(Tile.getTilesForLetters("FF"));
		tiles.add(new Tile('K'));
		tiles.add(new Tile('J'));
		tiles.add(new Tile('X'));
		tiles.add(new Tile('Q'));
		tiles.add(new Tile('Z'));
		Collections.shuffle(tiles);
		// fill the bag with tiles
		for (Tile tile : tiles) {
			this.bag.push(tile);
		}
	}
}
