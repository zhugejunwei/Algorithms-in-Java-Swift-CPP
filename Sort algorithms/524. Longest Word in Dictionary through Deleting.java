public class Solution {
    public String findLongestWord(String s, List<String> d) {
        Collections.sort(d, (a, b) -> (a.length() == b.length() ? a.compareTo(b) : b.length() - a.length()));
        for (String word : d) {
            int i = 0;
            for (char c : s.toCharArray()) {
                if (i < word.length() && c == word.charAt(i))
                    i++;
                if (i == word.length()) return word;
            }
        }
        return "";
    }
}
