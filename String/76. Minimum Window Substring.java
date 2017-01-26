public class Solution {
    public String minWindow(String s, String t) {
        int[] map = new int[128];
        for (char c : t.toCharArray()) map[c]++;
        int start = 0, end = 0, count = t.length(), len = Integer.MAX_VALUE, minStart = -1;
        while (end < s.length()) {
            if (map[s.charAt(end++)]-- > 0) count--;
            while (count < 0 || (start < s.length() && map[s.charAt(start)] < 0))
                if (map[s.charAt(start++)]++ == 0)
                    count++;
            if (count == 0 && len > end - start) {
                minStart = start;
                len = end - start;
            }
        }
        return minStart == -1 ? "" : s.substring(minStart, minStart + len);
    }
}
