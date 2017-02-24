// DP
public class Solution {
    public int longestSubstring(String s, int k) {
        int max = 0;
        char[] arr = s.toCharArray();
        for (int i = 0; i + k <= s.length();) {
            int[] count = new int[128];
            int mask = 0, next = i;
            for (int j = i; j < s.length(); j++) {
                count[arr[j]]++;
                
                // check whether there is a valid substring
                if (count[arr[j]] < k) mask |= (1 << arr[j]);
                else mask &= (~(1 << arr[j]));
                
                if (mask == 0) { // there is a valid substring
                    max = Math.max(max, j - i + 1);
                    next = j;
                }
            }
            i = next + 1;
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
