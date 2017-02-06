public class Solution {
    public boolean validWordAbbreviation(String word, String abbr) {
        if (word == null || word.length() == 0) return abbr == "0";
        int k = 0;
        for (int i = 0; i < abbr.length(); i++) {
            if (k >= word.length()) return false;
            if (abbr.charAt(i) >= 'a' && abbr.charAt(i) <= 'z' || abbr.charAt(i) == '0') {
                if (abbr.charAt(i) != word.charAt(k)) return false;
                k++;
            } else {
                int num = abbr.charAt(i) - '0';
                while (i + 1 < abbr.length() && abbr.charAt(i + 1) >= '0' && abbr.charAt(i + 1) <= '9') {
                    num = num * 10 + abbr.charAt(++i) - '0';
                }
                k += num;
            }
        }
        return k == word.length();
    }
}
