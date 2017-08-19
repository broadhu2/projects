package org.broadhurst.natalie.projects.scrabble;

public enum Marker {
	TRIPLE_WORD("|\u001b[31m TW \u001b[0m"),
	TRIPLE_LETTER("|\u001b[35;1m TL \u001b[0m"),
	DOUBLE_WORD("|\u001b[34;1m DW \u001b[0m"),
	DOUBLE_LETTER("|\u001b[36m DL \u001b[0m"),
	START("|\u001b[30;1m ** \u001b[0m"),
	NONE("|    ");
	
	private String value;
	
	private Marker(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return this.value;
	}
}
