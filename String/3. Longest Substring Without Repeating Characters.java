public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int[] map = new int[128];
        int count = 0, start = 0, end = 0, d = 0;
        while (end < s.length()) {
            if (map[s.charAt(end++)]++ > 0) count++;
            while (count > 0) if(map[s.charAt(start++)]-- > 1) count--;
            d = Math.max(d, end - start);
        }
        return d;
    }
}
