package org.broadhurst.natalie.projects.scrabble;

import java.util.List;

public class Board {
	private static final String[][] layout = {
		{ "TW", "  ", "  ", "DL", "  ", "  ", "  ", "TW", "  ", "  ", "  ", "DL", "  ", "  ", "TW" },
		{ "  ", "DW", "  ", "  ", "  ", "TL", "  ", "  ", "  ", "TL", "  ", "  ", "  ", "DW", "  " },
		{ "  ", "  ", "DW", "  ", "  ", "  ", "DL", "  ", "DL", "  ", "  ", "  ", "DW", "  ", "  " },
		{ "DL", "  ", "  ", "DW", "  ", "  ", "  ", "DL", "  ", "  ", "  ", "DW", "  ", "  ", "DL" },
		{ "  ", "  ", "  ", "  ", "DW", "  ", "  ", "  ", "  ", "  ", "DW", "  ", "  ", "  ", "  " },
		{ "  ", "TL", "  ", "  ", "  ", "TL", "  ", "  ", "  ", "TL", "  ", "  ", "  ", "TL", "  " },
		{ "  ", "  ", "DL", "  ", "  ", "  ", "DL", "  ", "DL", "  ", "  ", "  ", "DL", "  ", "  " },
		{ "TW", "  ", "  ", "DL", "  ", "  ", "  ", "**", "  ", "  ", "  ", "DL", "  ", "  ", "TW" },
		{ "  ", "  ", "DL", "  ", "  ", "  ", "DL", "  ", "DL", "  ", "  ", "  ", "DL", "  ", "  " },
		{ "  ", "TL", "  ", "  ", "  ", "TL", "  ", "  ", "  ", "TL", "  ", "  ", "  ", "TL", "  " },
		{ "  ", "  ", "  ", "  ", "DW", "  ", "  ", "  ", "  ", "  ", "DW", "  ", "  ", "  ", "  " },
		{ "DL", "  ", "  ", "DW", "  ", "  ", "  ", "DL", "  ", "  ", "  ", "DW", "  ", "  ", "DL" },
		{ "  ", "  ", "DW", "  ", "  ", "  ", "DL", "  ", "DL", "  ", "  ", "  ", "DW", "  ", "  " },
		{ "  ", "DW", "  ", "  ", "  ", "TL", "  ", "  ", "  ", "TL", "  ", "  ", "  ", "DW", "  " },
		{ "TW", "  ", "  ", "DL", "  ", "  ", "  ", "TW", "  ", "  ", "  ", "DL", "  ", "  ", "TW" }, 
	};

	private Square[][] squares;

	public Board() {
		initialize();
	}

	/**
	 * 
	 * @param startRow
	 * @param startColumn
	 * @param tiles
	 * @param horizontal
	 * @return the score for the tiles placed
	 */
	public int placeTiles(int startRow, int startColumn, 
			List<Tile> tiles, Direction direction) {
		if (isValidPlacement(startRow, startColumn, tiles, direction)) {
			if (direction.equals(Direction.HORIZONTAL)) {
				for (int i = 0; i < tiles.size(); i++) {
					this.squares[startRow][startColumn + i].setTile(tiles.get(i));
				}
			} else {
				for (int i = 0; i < tiles.size(); i++) {
					this.squares[startRow + i][startColumn].setTile(tiles.get(i));
				}
			}
		}
		return 0;
	}

	private boolean isValidPlacement(int startRow, int startColumn, 
			List<Tile> tiles, Direction direction) {
		return true;
	}

	private void initialize() {
		this.squares = new Square[15][15];
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				switch (layout[i][j]) {
				case "TW":
					this.squares[i][j] = new Square(Marker.TRIPLE_WORD);
					break;
				case "TL":
					this.squares[i][j] = new Square(Marker.TRIPLE_LETTER);
					break;
				case "DW":
					this.squares[i][j] = new Square(Marker.DOUBLE_WORD);
					break;
				case "DL":
					this.squares[i][j] = new Square(Marker.DOUBLE_LETTER);
					break;
				case "**":
					this.squares[i][j] = new Square(Marker.START);
					break;
				default:
					this.squares[i][j] = new Square();
				}
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("     0    1    2    3    4    5    6    7    8    9    10   11   12   13   14  \n");
		sb.append("   +----+----+----+----+----+----+----+----+----+----+----+----+----+----+----+\n");
		for (int i = 0; i < 15; i++) {
			sb.append(String.format("%2d ", i));
			for (int j = 0; j < 15; j++) {
				sb.append(squares[i][j].toString());
			}
			sb.append("|\n");
			sb.append("   +----+----+----+----+----+----+----+----+----+----+----+----+----+----+----+\n");
		}
		return sb.toString();
	}
}
