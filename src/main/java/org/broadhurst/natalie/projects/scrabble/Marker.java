package org.broadhurst.natalie.projects.scrabble;

public enum Marker {
	TRIPLE_WORD("|\u001b[31m TW \u001b[0m", Type.WORD, 3),
	TRIPLE_LETTER("|\u001b[35;1m TL \u001b[0m", Type.LETTER, 3),
	DOUBLE_WORD("|\u001b[34;1m DW \u001b[0m", Type.WORD, 2),
	DOUBLE_LETTER("|\u001b[36m DL \u001b[0m", Type.LETTER, 2),
	START("|\u001b[30;1m ** \u001b[0m", Type.WORD, 2),
	NONE("|    ", Type.NONE, 1);
	
	private String display;
	private Type type;
	private int multiplier;
	
	private Marker(String display, Type type, int multiplier) {
		this.display = display;
		this.type = type;
		this.multiplier = multiplier;
	}
	
	public Type getType() {
		return this.type;
	}
	
	public int getMultiplier() {
		return this.multiplier;
	}
	
	@Override
	public String toString() {
		return this.display;
	}
	
	public enum Type {
		WORD,
		LETTER,
		NONE
	}
}
