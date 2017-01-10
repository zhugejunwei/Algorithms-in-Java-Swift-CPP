public class Solution {
    public int longestSubstring(String s, int k) {
        int max = 0;
        for (int f = 0; f + k <= s.length(); ) {
            int[] count = new int[26];
            count[0] = 0;
            int mask = 0;
            int maxLast = f;
            for (int last = f; last < s.length(); ++last) {
                int i = s.charAt(last) - 'a';
                count[i]++;
                if (count[i] < k) mask |= (1 << i);
                else mask &= (~(1 << i));
                
                if (mask == 0) {
                    max = Math.max(max, last - f + 1);
                    maxLast = last;
                }
            }
            f = maxLast + 1;
        }
        return max;
    }
}

// faster

public class Solution {
    public int longestSubstring(String s, int k) {
        return helper(s, 0, s.length(), k);
    }
    
    private int helper(String s, int beg, int end, int k) {
        if (end - beg < k) return 0; // dont forget the terminate condition
        int[] count = new int[128];
        for (int i = beg; i < end; i++) ++count[s.charAt(i)];
        for (int i = beg; i < end; i++) {
            if (count[s.charAt(i)] < k) {
                int left = helper(s, beg, i, k);
                int right = helper(s, i + 1, end, k);
                return Math.max(left, right);
            }
        }
        return end - beg;
    }
}
