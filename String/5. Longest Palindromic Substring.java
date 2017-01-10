public class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return "";
        if (s.length() == 1) return s;
        int minStart = 0, maxLen = 1, len = s.length();
        for (int i = 0; i < len;) {
            if ((len - i) * 2 <= maxLen) break;
            int j = i, k = i;
            // skip same characters
            while (k + 1 < len && s.charAt(k) == s.charAt(k + 1)) ++k;
            i = k + 1;
            // expand
            while (j > 0 && k + 1 < len && s.charAt(k + 1) == s.charAt(j - 1)) {
                ++k;
                --j;
            }
            int newLen = k - j + 1;
            if (newLen > maxLen) {
                maxLen = newLen;
                minStart = j;
            }
        }
        return s.substring(minStart, minStart + maxLen);
    }
}
