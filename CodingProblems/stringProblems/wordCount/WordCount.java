package stringProblems.wordCount;

public class WordCount {
	static Integer stringWordCount(String input) {
		if (input == null)
			return null;

		boolean wordCountStarted = false;
		int wordCount = 0;
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) != ' ') {
				if (wordCountStarted)
					continue;
				else {
					wordCount++;
					wordCountStarted = true;
				}
			} else
				wordCountStarted = false;
		}
		return wordCount;
	}

	public static void main(String args[]) {
		System.out.println("null: " + stringWordCount(null));
		System.out.println("a:" + stringWordCount("a"));
		System.out.println(":" + stringWordCount(""));
		System.out.println(" :" + stringWordCount(" "));
		System.out.println("    :" + stringWordCount("    "));
		System.out.println(" a :" + stringWordCount(" a "));
		System.out.println("    aa:" + stringWordCount("    aa"));
		System.out.println("aa     :" + stringWordCount("aa     "));
		System.out.println("aa bb  cc  dd:" + stringWordCount("aa bb  cc  dd"));
		System.out.println(" a b c :" + stringWordCount(" a b c "));
		System.out.println("aaaaa bbb ccc d      e   f:" + stringWordCount("aaaaa bbb ccc d      e   f"));
	}
}
