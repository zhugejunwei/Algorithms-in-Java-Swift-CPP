public class Solution {
    public String minWindow(String s, String t) {
        int minStart = 0, minLen = -1;
        int[] map = new int[128];
        for (char c : t.toCharArray()) map[c]++;
        int start = 0, end = 0, count = t.length();
        while(end < s.length()) {
            if (map[s.charAt(end++)]-- > 0) count--;
            while (count == 0) {
                if (end - start < minLen || minLen == -1)
                    minLen = end - (minStart = start);
                if (map[s.charAt(start++)]++ == 0) count++;
            }
        }
        return minLen == -1 ? "" : s.substring(minStart, minStart + minLen);
    }
}
