package org.broadhurst.natalie.projects.scrabble;

import java.io.IOException;

import org.broadhurst.natalie.projects.scrabble.Exceptions.InvalidWordException;
import org.broadhurst.natalie.projects.scrabble.Exceptions.TilesCannotBePlacedException;

/**
 * Main class.
 */
public class Scrabble {
    public static void main(String[] args) throws IOException {
//        System.out.println("Let's play scrabble.");        
        char[][] crosswords = {
    		{' ', 'M', 'I', 'S', 'S', 'E', 'D', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
    		{' ', 'A', ' ', 'T', 'O', ' ', 'R', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
    		{' ', 'D', ' ', 'A', ' ', ' ', 'E', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
    		{' ', 'E', ' ', 'N', ' ', ' ', 'A', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
    		{' ', ' ', ' ', 'D', ' ', ' ', 'D', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
    		{' ', ' ', ' ', ' ', ' ', ' ', 'F', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
    		{' ', ' ', ' ', ' ', ' ', ' ', 'U', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
    		{' ', ' ', ' ', ' ', ' ', ' ', 'L', 'O', 'S', 'T', ' ', ' ', ' ', ' ', ' '},
    		{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
    		{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
    		{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
    		{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
    		{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
    		{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
    		{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
        };
        Board board = new Board(crosswords);
        System.out.println(board);
        System.out.println(board.placeTiles(2, 0, Tile.getTilesForLetters("AVNC"), Direction.HORIZONTAL));
        System.out.println(board);
        
//        board.setTileForSquare(8, 7, 'e');
//        board.setTileForSquare(9, 7, 'l');
//        board.setTileForSquare(11, 7, 'p');
//        List<Tile> tiles = Tile.getTilesForWord("tehone");
//        System.out.println(board.isWithinBounds(7, 7, tiles, Direction.VERTICAL));
//        System.out.println(board);
//        board.placeTiles(7, 4, Tile.getTilesForWord("hello"), Direction.HORIZONTAL);
//        System.out.println(board);
//        Game game = new Game();
//        List<Tile> tiles = game.drawTiles(7);
//        System.out.println(tiles);
//        System.out.println(tiles.size());    	
    }
}
