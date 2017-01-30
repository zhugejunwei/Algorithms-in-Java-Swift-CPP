import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Concordance {

	private class Word {
		private final ArrayList<Integer> occur = new ArrayList<Integer>();

		public Word(int occur) {
			this.occur.add(occur);
		}

		public String getOccurString() {
			try {
				String occur = "{" + this.occur.size() + ":"
						+ this.occur.get(0);
				this.occur.remove(0);
				for (int index : this.occur) {
					occur += "," + index;
				}
				return occur + "}";
			} catch (Throwable e) {
				throw new RuntimeException(e);
			}
		}

		public void addOccur(int pos) {
			occur.add(pos);
		}
	}

	private final String[] alphabet = { "a", "b", "c", "d", "e", "f", "g", "h",
			"i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u",
			"v", "w", "x", "y", "z" };
	private final HashMap<String, Word> wordMap = new HashMap<String, Word>();

	public void process(String document) {
		try {
			int sentenceCount = 0;
			for (String sentence : document.split("[.!?][)]*[\"]*\\s")) {
				if (sentence.length() > 0) {
					if (sentence.substring(0, 1).matches("[A-Z]")) {
						sentenceCount++;
					}
					for (String word : sentence.split(" ")) {
						word = word.replaceAll("[^a-zA-Z]", "").toLowerCase();
						if (!word.isEmpty()) {
							if (wordMap.containsKey(word)) {
								Word obj = wordMap.get(word);
								obj.addOccur(sentenceCount);
								wordMap.put(word, obj);
							} else {
								Word obj = new Word(sentenceCount);
								wordMap.put(word, obj);
							}
						}
					}
				}
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	public void printConcordance() {
		try {
			List<String> keyList = new ArrayList<String>(wordMap.keySet());
			java.util.Collections.sort(keyList);
			int index = 0;
			for (String key : keyList) {
				String print = alphabet[index] + ". " + key;
				if (print.length() <= 7) {
					print += "\t\t\t" + wordMap.get(key).getOccurString();
				} else {
					print += "\t\t" + wordMap.get(key).getOccurString();
				}
				System.out.println(print);
				alphabet[index] = alphabet[index] + alphabet[index].charAt(0);
				if (index == 25) {
					index = 0;
				} else {
					index++;
				}
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) throws Throwable {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		br.close();
		br = new BufferedReader(new FileReader(line));
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		br.close();
		Concordance concordance = new Concordance();
		concordance.process(sb.toString());
		concordance.printConcordance();
	}
}