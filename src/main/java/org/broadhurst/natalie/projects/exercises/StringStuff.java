package org.broadhurst.natalie.projects.exercises;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class has a set of (static) methods related to string manipulation for
 * you to implement. Static methods are methods that can be called directly on a
 * class, like this:
 * 
 * <p>
 * <code>StringStuff.hasUniqueCharacters();</code>
 * </p>
 * 
 * In contrast, non-static methods can only be called after creating a new
 * instance of a class, like this:
 * 
 * <p>
 * <code>StringStuff instance = new StringStuff();
 * <br>instance.hasUniqueCharacters();</code>
 * </p>
 * 
 * <p>
 * To test if your methods are working correctly, open the 'src/test/java'
 * folder in the Package Explorer on the left, then open the
 * 'org.broadhurst.natalie.projects.exercises' package, right click on the
 * StringStuffTest class, and choose Run As - JUnit Test.
 * 
 * <p>
 * Credit to "Cracking the Coding Interview" by Gayle Laakmann McDowell, which I
 * used for ideas for some of the methods.
 * 
 * @author Natalie
 *
 */
public class StringStuff {
	/**
	 * Empty private constructor (you can ignore this)
	 */
	private StringStuff() {
		// nothing to do here
	}

	/**
	 * Write a method to convert a string into a string where every character
	 * alternates between upper and lower case, starting with lower case. If there
	 * is any space between words or any non-letter characters, the alternation
	 * should continue from the opposite case of the last letter encountered. For
	 * example, "Alternating Caps" would become "aLtErNaTiNg CaPs". You may use the
	 * Java StringBuilder class and the Java Character API.
	 * 
	 * <p>
	 * Hint: use a boolean variable to track when to toggle the case and use the
	 * Character.isLetter() method to check for letter characters.
	 * 
	 * <p>
	 * Difficulty: <tt>MEDIUM</tt>
	 * 
	 * @param string
	 *            the string to operate on.
	 * @return the alternating caps string
	 */
	public static String alternatingCaps(final String string) {
		StringBuilder alternating = new StringBuilder();
		boolean caseType = false; // false for lower case, true for upper case
		for (int i = 0; i < string.length(); i++) {
			if (Character.isLetter(string.charAt(i))) {
				// only alternate case for letter characters
				if (caseType) {
					alternating.append(Character.toUpperCase(string.charAt(i)));
				} else {
					alternating.append(Character.toLowerCase(string.charAt(i)));
				}
				caseType = !caseType;
			} else {
				// otherwise, just add the character as is
				alternating.append(string.charAt(i));
			}
		}
		return alternating.toString();
	}

	/**
	 * Write a method to perform basic string compression by replacing repeated
	 * characters with a numeric count of repetitions. For example, the string
	 * "aabcccccaaa" would become "a2b1c5a3". If the compressed version of the
	 * string would not be smaller than the original string, return the original
	 * string instead. You may use the Java StringBuilder class.
	 * 
	 * <p>
	 * Difficulty: <tt>HARD</tt>
	 * 
	 * @param string
	 *            the string to operate on.
	 * @return the compressed string (or the original string, if applicable).
	 */
	public static String compressString(final String string) {
		// compressed string won't be shorter if there's less than 3 chars
		if (string.length() < 3) {
			return string;
		}
		int count = 1;
		StringBuilder compressed = new StringBuilder();
		char prev = string.charAt(0);
		compressed.append(prev);
		for (int i = 1; i < string.length(); i++) {
			if (string.charAt(i) == prev) {
				count++;
			} else {
				compressed.append(count);
				prev = string.charAt(i);
				compressed.append(prev);
				count = 1;
			}
		}
		// add the count for the final char
		compressed.append(count);
		// only return the compressed string if it is shorter than original
		if (compressed.length() < string.length()) {
			return compressed.toString();
		} else {
			return string;
		}
	}

	/**
	 * Write a method to copy a list of strings and sort the copied list in
	 * alphabetical order. You may use the Java ArrayList class constructor and the
	 * Java Collections API.
	 * 
	 * <p>
	 * Difficulty: <tt>EASY</tt>
	 * 
	 * @param list
	 *            the list to operate on.
	 * @return a sorted copy of the list.
	 */
	public static List<String> copyAndSortList(final List<String> list) {
		List<String> listCopy = new ArrayList<>(list);
		Collections.sort(listCopy);
		return listCopy;
	}

	/**
	 * Write a method to find the longest string in a list of strings. If there are
	 * multiple words with the same longest length, return the first one
	 * encountered. If there are no words in the list, return an empty string.
	 * 
	 * <p>
	 * Difficulty: <tt>EASY</tt>
	 * 
	 * @param list
	 *            the list to operate on.
	 * @return the longest string in the list.
	 */
	public static String getLongestWord(final List<String> list) {
		int maxLength = 0;
		String longestWord = "";
		for (String word : list) {
			if (word.length() > maxLength) {
				maxLength = word.length();
				longestWord = word;
			}
		}
		return longestWord;
	}

	/**
	 * Write a method to find the index of the longest string in a list of strings.
	 * If there are multiple words with the same longest length, return the lesser
	 * index. If there are no words in the list, return 0.
	 * 
	 * <p>
	 * Difficulty: <tt>EASY</tt>
	 * 
	 * @param list
	 *            the list to operate on.
	 * @return the index of the longest string.
	 */
	public static int getLongestWordIndex(final List<String> list) {
		int maxLength = 0;
		int longestIndex = 0;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).length() > maxLength) {
				maxLength = list.get(i).length();
				longestIndex = i;
			}
		}
		return longestIndex;
	}

	/**
	 * Write a method to determine if a string has all unique characters. You may
	 * use the Java Set interface and HashSet class constructor.
	 * 
	 * <p>
	 * Difficulty: <tt>MEDIUM</tt>
	 * 
	 * @param string
	 *            the string to operate on.
	 * @return true if the string contains all unique characters; false otherwise.
	 */
	public static boolean hasUniqueCharacters(final String string) {
		Set<Character> encountered = new HashSet<>();
		for (int i = 0; i < string.length(); i++) {
			if (!encountered.add(string.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Write a method to determine if a string is a palindrome. A palindrome is a
	 * sequence of characters which reads the same backward and forward. Assume the
	 * string is a single word with only alphabetic characters. For example, "madam"
	 * and "abba" are palindromes.
	 * 
	 * <p>
	 * Difficulty: <tt>HARD</tt>
	 * 
	 * @param string
	 *            the string to operate on.
	 * @return true if the string is a palindrome; false otherwise.
	 */
	public static boolean isPalindrome(final String string) {
		int length = string.length();
		for (int indexFromLeft = 0; indexFromLeft < length / 2; indexFromLeft++) {
			int indexFromRight = length - indexFromLeft - 1;
			if (string.charAt(indexFromLeft) != string.charAt(indexFromRight)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Write a method to reverse the characters in a string. You can write this
	 * method as a one-line return statement using the Java StringBuilder
	 * constructor and two of the StringBuilder methods.
	 * 
	 * <p>
	 * Difficulty: <tt>EASY</tt>
	 * 
	 * @param string
	 *            the string to operate on.
	 * @return the reversed string.
	 */
	public static String reverseString(final String string) {
		return new StringBuilder(string).reverse().toString();
	}

	/**
	 * Write a method that cleans up a string according to a set of rules. This
	 * method should perform the following operations on a string:
	 * 
	 * <p>
	 * 1. Trim leading and trailing whitespace, e.g., " hello " becomes "hello".
	 * <br>
	 * 2. Reduce any occurrences of multiple spaces to one space, e.g. "hello there"
	 * becomes "hello there". </br>
	 * 3. Capitalize the first letter of each word and convert other letters to
	 * lower case. e.g., "test STRING" becomes "Test String". <br>
	 * 
	 * <p>
	 * If the original string is empty, or the trimmed string is empty, return an
	 * empty string. You may use the Java String API and the Java StringBuilder
	 * class.
	 * 
	 * <p>
	 * Hint: For a quick way to perform last two rules, you can split the string
	 * into words by using <code>string.split("\\s+")</code>. The parameter
	 * <code>"\\s+"</code> is a regular expression that searches for one or more
	 * whitespace characters as the delimiter.
	 * 
	 * <p>
	 * Difficulty: <tt>HARD</tt>
	 * 
	 * @param string
	 *            the string to operate on.
	 * @return the scrubbed string.
	 */
	public static String scrubString(final String string) {
		if (string.isEmpty() || string.trim().isEmpty()) {
			return "";
		}
		StringBuilder scrubbed = new StringBuilder();
		String copy = string.trim();
		String[] words = copy.split("\\s+");
		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			scrubbed.append(Character.toUpperCase(word.charAt(0)));
			if (word.length() > 1) {
				// the word has more than one letter, so it's safe to access later indices
				for (int j = 1; j < word.length(); j++) {
					scrubbed.append(Character.toLowerCase(word.charAt(j)));
				}
			}
			// only add a space if it's not the last word
			if (i < words.length - 1) {
				scrubbed.append(' ');
			}
		}
		return scrubbed.toString();
	}
}
