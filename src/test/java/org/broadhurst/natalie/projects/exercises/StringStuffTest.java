package org.broadhurst.natalie.projects.exercises;

import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StringStuffTest {

	@Rule
	public ErrorCollector collector = new ErrorCollector();

	@Test
	public void testAlternatingCaps() {
		collector.checkThat(StringStuff.alternatingCaps(""), equalTo(""));
		collector.checkThat(StringStuff.alternatingCaps("A"), equalTo("a"));
		collector.checkThat(StringStuff.alternatingCaps("AB"), equalTo("aB"));
		collector.checkThat(StringStuff.alternatingCaps("Alternating Caps"), equalTo("aLtErNaTiNg CaPs"));
		collector.checkThat(StringStuff.alternatingCaps("This should  be   alternated."),
				equalTo("tHiS sHoUlD  bE   aLtErNaTeD."));
		collector.checkThat(StringStuff.alternatingCaps(
				"Maya Angelo said, \"If you don't like something, change it. If you can't change it, change your attitude!\""),
				equalTo("mAyA aNgElO sAiD, \"iF yOu DoN't LiKe SoMeThInG, cHaNgE iT. iF yOu CaN't ChAnGe It, ChAnGe YoUr AtTiTuDe!\""));
	}

	@Test
	public void testCompressString() {
		collector.checkThat(StringStuff.compressString(""), equalTo(""));
		collector.checkThat(StringStuff.compressString("a"), equalTo("a"));
		collector.checkThat(StringStuff.compressString("aa"), equalTo("aa"));
		collector.checkThat(StringStuff.compressString("abc"), equalTo("abc"));
		collector.checkThat(StringStuff.compressString("aaa"), equalTo("a3"));
		collector.checkThat(StringStuff.compressString("aabcccccaaa"), equalTo("a2b1c5a3"));
		collector.checkThat(StringStuff.compressString("brrrr shhhhh zzzzzz"), equalTo("b1r4 1s1h5 1z6"));
		collector.checkThat(StringStuff.compressString("Brilliant, because bacon bites beat bruschetta"),
				equalTo("Brilliant, because bacon bites beat bruschetta"));
		collector.checkThat(
				StringStuff.compressString("00011110101101110000111011101100000100111001111000000100000010000000"),
				equalTo("03140111011201130413011301120511021302140611061107"));
	}

	@Test
	public void testCopyAndSortList() {
		List<String> list = Arrays.asList("Arizona", "Alabama", "Arkansas", "Alaska");
		List<String> listCopy = StringStuff.copyAndSortList(list);
		collector.checkThat("original list should not be modified", list,
				equalTo(Arrays.asList("Arizona", "Alabama", "Arkansas", "Alaska")));
		collector.checkThat(listCopy, equalTo(Arrays.asList("Alabama", "Alaska", "Arizona", "Arkansas")));
		collector.checkThat(
				StringStuff.copyAndSortList(Arrays.asList("copper", "explain", "truck", "neat", "unite", "branch",
						"educated", "tenuous", "hum", "decisive", "notice")),
				equalTo(Arrays.asList("branch", "copper", "decisive", "educated", "explain", "hum", "neat", "notice",
						"tenuous", "truck", "unite")));
		collector.checkThat(Collections.emptyList(), equalTo(Collections.emptyList()));
	}

	@Test
	public void testGetLongestWord() {
		collector.checkThat(StringStuff.getLongestWord(new ArrayList<String>()), equalTo(""));
		collector.checkThat(StringStuff.getLongestWord(Arrays.asList("the")), equalTo("the"));
		collector.checkThat(
				StringStuff.getLongestWord(Arrays.asList("late", "rambunctious", "plain", "annoyed", "chemical")),
				equalTo("rambunctious"));
		collector.checkThat(StringStuff.getLongestWord(Arrays.asList("standing", "humdrum", "border", "cluttered",
				"person", "alike", "unpack", "parcel", "puncture", "moor")), equalTo("cluttered"));
		collector.checkThat(StringStuff.getLongestWord(Arrays.asList("face", "write", "command", "loutish", "clean")),
				equalTo("command"));
	}

	@Test
	public void testGetLongestWordIndex() {
		collector.checkThat(StringStuff.getLongestWordIndex(new ArrayList<String>()), equalTo(0));
		collector.checkThat(StringStuff.getLongestWordIndex(Arrays.asList("the")), equalTo(0));
		collector.checkThat(
				StringStuff.getLongestWordIndex(Arrays.asList("late", "rambunctious", "plain", "annoyed", "chemical")),
				equalTo(1));
		collector.checkThat(StringStuff.getLongestWordIndex(Arrays.asList("standing", "humdrum", "border", "cluttered",
				"person", "alike", "unpack", "parcel", "puncture", "moor")), equalTo(3));
		collector.checkThat(
				StringStuff.getLongestWordIndex(Arrays.asList("face", "write", "command", "loutish", "clean")),
				equalTo(2));
	}

	@Test
	public void testHasUniqueCharacters() {
		collector.checkThat(StringStuff.hasUniqueCharacters(""), equalTo(true));
		collector.checkThat(StringStuff.hasUniqueCharacters("a"), equalTo(true));
		collector.checkThat(StringStuff.hasUniqueCharacters("uncopyrightable"), equalTo(true));
		collector.checkThat(StringStuff.hasUniqueCharacters("subdermatoglyphic"), equalTo(true));
		collector.checkThat(StringStuff.hasUniqueCharacters("aa"), equalTo(false));
		collector.checkThat(StringStuff.hasUniqueCharacters("yellow"), equalTo(false));
		collector.checkThat(StringStuff.hasUniqueCharacters("euouae"), equalTo(false));
	}

	@Test
	public void testIsPalindrome() {
		collector.checkThat(StringStuff.isPalindrome("madam"), equalTo(true));
		collector.checkThat(StringStuff.isPalindrome("abba"), equalTo(true));
		collector.checkThat(StringStuff.isPalindrome("racecar"), equalTo(true));
	}

	@Test
	public void testReverseString() {
		collector.checkThat(StringStuff.reverseString(""), equalTo(""));
		collector.checkThat(StringStuff.reverseString("racecar"), equalTo("racecar"));
		collector.checkThat(StringStuff.reverseString("reverse"), equalTo("esrever"));
		collector.checkThat(StringStuff.reverseString("A man, a plan, a canal - Panama!"),
				equalTo("!amanaP - lanac a ,nalp a ,nam A"));
	}

	@Test
	public void testScrubString() {
		collector.checkThat(StringStuff.scrubString(""), equalTo(""));
		collector.checkThat(StringStuff.scrubString(" "), equalTo(""));
		collector.checkThat(StringStuff.scrubString("   test   THAT this   is      workING  "),
				equalTo("Test That This Is Working"));
		collector.checkThat(StringStuff.scrubString(
				"   This  is    a tricky     test;       I  USED   punctUAtion    and a Couple     singLE   leTTers!  "),
				equalTo("This Is A Tricky Test; I Used Punctuation And A Couple Single Letters!"));
	}

}
