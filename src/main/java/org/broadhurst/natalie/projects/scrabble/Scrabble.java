package org.broadhurst.natalie.projects.scrabble;

import java.util.List;

/**
 * Main class.
 */
public class Scrabble {
    public static void main(String[] args) {
        System.out.println("Let's play scrabble.");
        Board board = new Board();
//        System.out.println(board);
        board.placeTiles(7, 4, Tile.getTilesForWord("hello"), Direction.HORIZONTAL);
        System.out.println(board);
        Game game = new Game();
        List<Tile> tiles = game.drawTiles(7);
        System.out.println(tiles);
        System.out.println(tiles.size());
    }
}
