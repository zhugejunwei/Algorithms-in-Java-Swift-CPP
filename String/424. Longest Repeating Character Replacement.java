public class Solution {
    public int characterReplacement(String s, int k) {
        int[] map = new int[128];
        int res = 0, start = 0, end = 0, maxCount = 0;
        while (end < s.length()) {
            if (++map[s.charAt(end++)] > maxCount) {
                maxCount = map[s.charAt(end-1)];
            }
            while (end - start - maxCount > k) {
                if (map[s.charAt(start++)]-- == maxCount) {
                    maxCount--;
                    for (int i = 'A'; i <= 'Z'; i++) {
                        if (map[i] > maxCount) {
                            maxCount = map[i];
                        }
                    }
                }
            }
            res = Math.max(res, end - start);
        }
        return res;
    }
}

// more concise solution

public class Solution {
    public int characterReplacement(String s, int k) {
        int[] map = new int[128];
        int start = 0, end = 0, maxCount = 0, res = 0;
        while (end < s.length()) {
            maxCount = Math.max(maxCount, ++map[s.charAt(end++)]);
            while (end - start - maxCount > k) {
                map[s.charAt(start++)]--;
            }
            res = Math.max(end - start, res);
        }
        return res;
    }
}
