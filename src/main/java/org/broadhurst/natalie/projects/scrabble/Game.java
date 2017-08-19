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
		tiles.addAll(Tile.getTilesForWord("EEEEEEEEEEEE"));
		tiles.addAll(Tile.getTilesForWord("AAAAAAAAA"));
		tiles.addAll(Tile.getTilesForWord("IIIIIIIII"));
		tiles.addAll(Tile.getTilesForWord("OOOOOOOO"));
		tiles.addAll(Tile.getTilesForWord("TTTTTT"));
		tiles.addAll(Tile.getTilesForWord("RRRRRR"));
		tiles.addAll(Tile.getTilesForWord("NNNNNN"));
		tiles.addAll(Tile.getTilesForWord("UUUU"));
		tiles.addAll(Tile.getTilesForWord("SSSS"));
		tiles.addAll(Tile.getTilesForWord("LLLL"));
		tiles.addAll(Tile.getTilesForWord("DDDD"));
		tiles.addAll(Tile.getTilesForWord("GGG"));
		tiles.addAll(Tile.getTilesForWord("??"));
		tiles.addAll(Tile.getTilesForWord("PP"));
		tiles.addAll(Tile.getTilesForWord("MM"));
		tiles.addAll(Tile.getTilesForWord("CC"));
		tiles.addAll(Tile.getTilesForWord("BB"));
		tiles.addAll(Tile.getTilesForWord("YY"));
		tiles.addAll(Tile.getTilesForWord("WW"));
		tiles.addAll(Tile.getTilesForWord("VV"));
		tiles.addAll(Tile.getTilesForWord("HH"));
		tiles.addAll(Tile.getTilesForWord("FF"));
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
