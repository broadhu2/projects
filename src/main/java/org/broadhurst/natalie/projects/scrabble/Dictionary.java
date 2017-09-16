package org.broadhurst.natalie.projects.scrabble;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.collections4.Trie;
import org.apache.commons.collections4.trie.PatriciaTrie;

public class Dictionary {
	private static Dictionary instance = null;
	private static Trie<String, Boolean> trie = new PatriciaTrie<>();

	private Dictionary() {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("words.txt").getFile());
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			for (String line = br.readLine(); line != null; line = br.readLine()) {	
				trie.put(line, true);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static Dictionary getInstance() {
		if (instance == null) {
			instance = new Dictionary();
		}
		return instance;
	}
	
	public static boolean isValidWord(String word) {
		return Dictionary.getInstance().getTrie().containsKey(word.toUpperCase());
	}
	
	private Trie<String, Boolean> getTrie() {
		return Dictionary.trie;
	}
}
