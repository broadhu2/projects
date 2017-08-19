package org.broadhurst.natalie.projects.scrabble;

public class Square {
	private Tile tile;
	private Marker marker;
	
	public Square() {
		this.tile = new Tile();
		this.marker = Marker.NONE;
	}
	
	public Square(Marker marker) {
		this.tile = new Tile();
		this.marker = marker;
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
}
