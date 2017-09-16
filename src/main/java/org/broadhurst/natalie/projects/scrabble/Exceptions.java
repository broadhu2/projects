package org.broadhurst.natalie.projects.scrabble;

import java.util.Iterator;

@SuppressWarnings("serial")
public final class Exceptions {
	private Exceptions() {}

	public static class ScrabbleException extends Exception {
		public ScrabbleException(String message) {
			super(message);
		}
	}

	public static class InvalidWordException extends ScrabbleException {
		public InvalidWordException(String word) {
			super("'" + word + "' is not a valid word.");
		}
	}

	public static class InvalidStartPosition extends ScrabbleException {
		public InvalidStartPosition(int x, int y) {
			super("[" + x + "][" + y + "] is not a valid starting position.");
		}
	}

	public static class InvalidFirstMove extends ScrabbleException {
		public InvalidFirstMove() {
			super("At least one tile must touch the center square on the first move.");
		}
	}

	public static class TilesCannotBePlacedException extends ScrabbleException {
		public TilesCannotBePlacedException(boolean touching, Iterator<Tile> it) {
			super(getFailureMessage(touching, it));
		}

		private static String getFailureMessage(boolean touching, Iterator<Tile> it) {
			if (!touching) {
				return "Newly placed tiles must touch an existing word.";
			} else {
				StringBuilder remainingTiles = new StringBuilder();
				while (it.hasNext()) {
					remainingTiles.append(it.next().getLetter());
				}
				return "Tiles '" + remainingTiles.toString() + "' cannot fit onto board.";
			}
		}
	}
}
