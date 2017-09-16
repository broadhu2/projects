package org.broadhurst.natalie.projects.scrabble;

public final class Square {
	private int row;
	private int col;
	private Tile tile;
	private Marker marker;
	
	public Square(int row, int col) {
		this.row = row;
		this.col = col;
		this.tile = new Tile();
		this.marker = Marker.NONE;
	}
	
	public Square(int row, int col, Marker marker) {
		this.row = row;
		this.col = col;
		this.tile = new Tile();
		this.marker = marker;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public boolean hasTile() {
		return this.tile.getLetter() != '\0';
	}
	
	public Tile getTile() {
		return this.tile;
	}
	
	public void setTile(Tile tile) {
		this.tile = tile;
	}
	
	public Marker getMarker() {
		return this.marker;
	}
	
	@Override
	public String toString() {
		if (this.hasTile()) {
			return this.tile.toString();
		} else {
			return this.marker.toString();
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + row;
		result = prime * result + col;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Square other = (Square) obj;
		return (row == other.row && col == other.col);
	}	
}
