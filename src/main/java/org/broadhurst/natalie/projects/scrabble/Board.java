package org.broadhurst.natalie.projects.scrabble;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.Range;
import org.broadhurst.natalie.projects.scrabble.Exceptions.InvalidFirstMove;
import org.broadhurst.natalie.projects.scrabble.Exceptions.InvalidStartPosition;
import org.broadhurst.natalie.projects.scrabble.Exceptions.InvalidWordException;
import org.broadhurst.natalie.projects.scrabble.Exceptions.ScrabbleException;
import org.broadhurst.natalie.projects.scrabble.Exceptions.TilesCannotBePlacedException;
import org.broadhurst.natalie.projects.scrabble.Marker.Type;

public class Board {
	private static final String[][] DEFAULT_LAYOUT = {
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
			{ "TW", "  ", "  ", "DL", "  ", "  ", "  ", "TW", "  ", "  ", "  ", "DL", "  ", "  ", "TW" }, };

	private Square[][] squares;

	public Board() {
		initialize();
	}

	public Board(char[][] crosswords) {
		initialize();
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				if (Character.isLetter(crosswords[i][j])) {
					squares[i][j].setTile(new Tile(Character.toUpperCase(crosswords[i][j])));
				}
			}
		}
	}

	private boolean checkStartConditions(int row, int col, List<Tile> tiles) {
		if (!isValidStartPosition(row, col)) {
			System.out.println("[" + row + "][" + col + "] is not a valid starting position.");
			return false;
		} else if (isFirstMove() && !isValidFirstMove(squares[row][col], tiles)) {
			System.out.println("At least one tile must touch the center square on the first move.");
			return false;
		}
		return true;
	}

	private boolean isValidStartPosition(int row, int col) {
		return Range.between(0, 14).contains(row) && Range.between(0, 14).contains(col) && !squares[row][col].hasTile();
	}

	private boolean isFirstMove() {
		return !squares[7][7].hasTile();
	}

	private boolean isValidFirstMove(Square start, List<Tile> tiles) {
		if ((start.getRow() == 7 && Range.between(start.getCol(), start.getCol() + tiles.size()).contains(7))
				|| (start.getCol() == 7 && Range.between(start.getRow(), start.getRow() + tiles.size()).contains(7))) {
			StringBuilder word = new StringBuilder();
			for (Tile tile : tiles) {
				word.append(tile.getLetter());
			}
			return checkIfValidWord(word.toString());
		} else {
			return false;
		}
	}

	public int placeTiles(int row, int col, List<Tile> tiles, Direction direction) {
		checkStartConditions(row, col, tiles);
		Square start = squares[row][col];
		int score = 0;
		if (direction.equals(Direction.HORIZONTAL)) {
			if (isValidHorizontalMove(start, tiles)) {
				score = makeMoveHorizontally(start, tiles);
			}
		} else {
			if (isValidVerticalMove(start, tiles)) {
				score = makeMoveVertically(start, tiles);
			}
		}
		return score;
	}

	public int makeMoveHorizontally(Square start, List<Tile> tiles) {
		int score = 0;
		int crosswordScore = 0;
		int wordMultiplier = 0;
		Iterator<Tile> it = tiles.iterator();
		Square curr = start;

		while (!atLeftMostSquare(curr)) {
			curr = getSquareToLeft(curr);
		}

		// evaluate the tile placement from the left to right
		while (!atRightBoundaryOfBoard(curr)) {
			if (curr.hasTile()) {
				score += curr.getTile().getPoints();
			} else if (it.hasNext()) {
				Tile tile = it.next();
				crosswordScore += getVerticalCrosswordScore(curr, tile);
				// place the tile on the board
				curr.setTile(tile);
				if (curr.getMarker().getType().equals(Type.WORD)) {
					// save word multipliers for later
					wordMultiplier += curr.getMarker().getMultiplier();
					score += tile.getPoints();
				} else {
					score += tile.getPoints() * curr.getMarker().getMultiplier();
				}
			} else {
				break;
			}
			curr = getSquareToRight(curr);
		}

		if (wordMultiplier == 0) {
			// no word multipliers, return crosswordScore + score as is
			return crosswordScore + score;
		} else {
			return crosswordScore + score * wordMultiplier;
		}
	}

	public int getVerticalCrosswordScore(Square square, Tile tile) {
		int crosswordScore = 0;
		int wordMultiplier = 0;
		Square curr = square;

		if (hasNoNeighborsAboveOrBelow(square)) {
			return 0;
		}

		while (!atTopMostSquare(curr)) {
			curr = getSquareAbove(curr);
		}

		// evaluate the crossword from top to bottom
		while (!atBottomBoundaryOfBoard(curr)) {
			if (curr.equals(square)) {
				if (curr.getMarker().getType().equals(Type.WORD)) {
					// save word multipliers for later
					wordMultiplier += curr.getMarker().getMultiplier();
					crosswordScore += tile.getPoints();
				} else {
					crosswordScore += tile.getPoints() * curr.getMarker().getMultiplier();
				}
			} else if (curr.hasTile()) {
				crosswordScore += tile.getPoints();
			} else {
				break;
			}
			curr = getSquareBelow(curr);
		}

		if (wordMultiplier == 0) {
			// no word multipliers, return score as is
			return crosswordScore;
		} else {
			return crosswordScore * wordMultiplier;
		}
	}

	public int makeMoveVertically(Square start, List<Tile> tiles) {
		int score = 0;
		int crosswordScore = 0;
		int wordMultiplier = 0;
		Iterator<Tile> it = tiles.iterator();
		Square curr = start;

		while (!atTopMostSquare(curr)) {
			curr = getSquareAbove(curr);
		}

		// evaluate the tile placement from the left to right
		while (!atBottomBoundaryOfBoard(curr)) {
			if (curr.hasTile()) {
				score += curr.getTile().getPoints();
			} else if (it.hasNext()) {
				Tile tile = it.next();
				crosswordScore += getHorizontalCrosswordScore(curr, tile);
				// place the tile on the board
				curr.setTile(tile);
				if (curr.getMarker().getType().equals(Type.WORD)) {
					// save word multipliers for later
					wordMultiplier += curr.getMarker().getMultiplier();
					score += tile.getPoints();
				} else {
					score += tile.getPoints() * curr.getMarker().getMultiplier();
				}
			} else {
				break;
			}
			curr = getSquareBelow(curr);
		}

		if (wordMultiplier == 0) {
			// no word multipliers, return crosswordScore + score as is
			return crosswordScore + score;
		} else {
			return crosswordScore + score * wordMultiplier;
		}
	}

	public int getHorizontalCrosswordScore(Square square, Tile tile) {
		int crosswordScore = 0;
		int wordMultiplier = 0;
		Square curr = square;

		if (hasNoNeighborsToLeftOrRight(square)) {
			return 0;
		}

		while (!atLeftMostSquare(curr)) {
			curr = getSquareToLeft(curr);
		}

		// evaluate the crossword from top to bottom
		while (!atRightBoundaryOfBoard(curr)) {
			if (curr.equals(square)) {
				if (curr.getMarker().getType().equals(Type.WORD)) {
					// save word multipliers for later
					wordMultiplier += curr.getMarker().getMultiplier();
					crosswordScore += tile.getPoints();
				} else {
					crosswordScore += tile.getPoints() * curr.getMarker().getMultiplier();
				}
			} else if (curr.hasTile()) {
				crosswordScore += tile.getPoints();
			} else {
				break;
			}
			curr = getSquareToRight(curr);
		}

		if (wordMultiplier == 0) {
			// no word multipliers, return score as is
			return crosswordScore;
		} else {
			return crosswordScore * wordMultiplier;
		}
	}

	private boolean isValidHorizontalMove(Square start, List<Tile> tiles) {
		boolean touching = false;
		StringBuilder word = new StringBuilder();
		Iterator<Tile> it = tiles.iterator();
		Square curr = start;

		while (!atLeftMostSquare(curr)) {
			curr = getSquareToLeft(curr);
		}

		// evaluate the tile placement from the left to right
		while (!atRightBoundaryOfBoard(curr)) {
			if (curr.hasTile()) {
				word.append(curr.getTile().getLetter());
				touching = true;
			} else if (it.hasNext()) {
				Tile tile = it.next();
				if (makesValidVerticalCrossword(curr, tile)) {
					word.append(tile.getLetter());
					touching = true;
				}
			} else {
				break;
			}
			curr = getSquareToRight(curr);
		}

		return checkEndConditions(touching, it, word.toString());
	}

	private boolean makesValidVerticalCrossword(Square square, Tile tile) {
		Square curr = square;
		StringBuilder crossword = new StringBuilder();

		if (hasNoNeighborsAboveOrBelow(square)) {
			return true;
		}

		while (!atTopMostSquare(curr)) {
			curr = getSquareAbove(curr);
		}

		// evaluate the crossword from top to bottom
		while (!atBottomBoundaryOfBoard(curr)) {
			if (curr.equals(square)) {
				crossword.append(tile.getLetter());
			} else if (curr.hasTile()) {
				crossword.append(curr.getTile().getLetter());
			} else {
				break;
			}
			curr = getSquareBelow(curr);
		}

		return checkIfValidWord(crossword.toString());
	}

	public boolean isValidVerticalMove(Square start, List<Tile> tiles) {
		boolean touching = false;
		StringBuilder word = new StringBuilder();
		Iterator<Tile> it = tiles.iterator();
		Square curr = start;

		while (!atTopMostSquare(curr)) {
			curr = getSquareAbove(curr);
		}

		// evaluate the tile placement from the top to bottom
		while (!atBottomBoundaryOfBoard(curr)) {
			if (curr.hasTile()) {
				word.append(curr.getTile().getLetter());
				touching = true;
			} else if (it.hasNext()) {
				Tile tile = it.next();
				if (makesValidHorizontalCrossword(curr, tile)) {
					word.append(tile.getLetter());
					touching = true;
				}
			} else {
				break;
			}
			curr = getSquareBelow(curr);
		}

		return checkEndConditions(touching, it, word.toString());
	}

	private boolean makesValidHorizontalCrossword(Square square, Tile tile) {
		Square curr = square;
		StringBuilder crossword = new StringBuilder();

		if (hasNoNeighborsToLeftOrRight(curr)) {
			return true;
		}

		while (!atLeftMostSquare(curr)) {
			curr = getSquareToLeft(curr);
		}

		// evaluate the crossword from left to right
		while (!atRightBoundaryOfBoard(curr)) {
			if (curr.equals(square)) {
				crossword.append(tile.getLetter());
			} else if (curr.hasTile()) {
				crossword.append(curr.getTile().getLetter());
			} else {
				break;
			}
			curr = getSquareToRight(curr);
		}

		return checkIfValidWord(crossword.toString());
	}

	private boolean checkEndConditions(boolean touching, Iterator<Tile> it, String word) {
		if (!touching) {
			System.out.println("Newly placed tiles must touch an existing word.");
			return false;
		} else if (it.hasNext()) {
			StringBuilder remainingTiles = new StringBuilder();
			while (it.hasNext()) {
				remainingTiles.append(it.next().getLetter());
			}
			System.out.println("Tiles '" + remainingTiles.toString() + "' cannot fit onto board.");
			return false;
		} else {
			return checkIfValidWord(word);
		}
	}

	private Square getSquareAbove(Square square) {
		if (square.getRow() == 0) {
			return square;
		} else {
			return squares[square.getRow() - 1][square.getCol()];
		}
	}

	private Square getSquareBelow(Square square) {
		if (square.getRow() == 14) {
			return square;
		} else {
			return squares[square.getRow() + 1][square.getCol()];
		}
	}

	private Square getSquareToLeft(Square square) {
		if (square.getCol() == 0) {
			return square;
		} else {
			return squares[square.getRow()][square.getCol() - 1];
		}
	}

	private Square getSquareToRight(Square square) {
		if (square.getCol() == 14) {
			return square;
		} else {
			return squares[square.getRow()][square.getCol() + 1];
		}
	}

	private boolean hasNoNeighborsAboveOrBelow(Square square) {
		return !getSquareAbove(square).hasTile() && !getSquareBelow(square).hasTile();
	}

	private boolean hasNoNeighborsToLeftOrRight(Square square) {
		return !getSquareToLeft(square).hasTile() && !getSquareToRight(square).hasTile();
	}

	private boolean atTopMostSquare(Square square) {
		return square.equals(getSquareAbove(square)) || !getSquareAbove(square).hasTile();
	}

	private boolean atLeftMostSquare(Square square) {
		return square.equals(getSquareToLeft(square)) || !getSquareToLeft(square).hasTile();
	}

	private boolean atBottomBoundaryOfBoard(Square square) {
		return square.equals(getSquareBelow(square));
	}

	private boolean atRightBoundaryOfBoard(Square square) {
		return square.equals(getSquareToRight(square));
	}

	private boolean checkIfValidWord(String word) {
		if (!Dictionary.isValidWord(word)) {
			System.out.println("'" + word + "' is not a valid word.");
			return false;
		} else {
			return true;
		}
	}

	private void initialize() {
		squares = new Square[15][15];
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				switch (DEFAULT_LAYOUT[i][j]) {
				case "TW":
					squares[i][j] = new Square(i, j, Marker.TRIPLE_WORD);
					break;
				case "TL":
					squares[i][j] = new Square(i, j, Marker.TRIPLE_LETTER);
					break;
				case "DW":
					squares[i][j] = new Square(i, j, Marker.DOUBLE_WORD);
					break;
				case "DL":
					squares[i][j] = new Square(i, j, Marker.DOUBLE_LETTER);
					break;
				case "**":
					squares[i][j] = new Square(i, j, Marker.START);
					break;
				default:
					squares[i][j] = new Square(i, j);
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
