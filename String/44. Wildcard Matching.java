public class Solution {
    public boolean isMatch(String s, String p) {
        int i = 0, j = 0, match = 0, star = -1;
        while (i < s.length()) {
            if (j < p.length() && (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i))) {
                i++;
                j++;
                continue;
            }
            if (j < p.length() && p.charAt(j) == '*') {
                match = i;
                star = j++;
                continue;
            }
            if (star >= 0) {
                i = ++match;
                j = star + 1;
                continue;
            }
            return false;
        }
        while (j < p.length()) {
            if (p.charAt(j++) != '*') return false;
        }
        return true;
    }
}

// DP
public class Solution {
    public boolean isMatch(String s, String p) {
        int slen = s.length(), plen = p.length();
        boolean[][] dp = new boolean[slen + 1][plen + 1];
        dp[slen][plen] = true;
        for (int j = plen - 1; j >= 0; j--) {
            dp[slen][j] = p.charAt(j) == '*' && dp[slen][j + 1];
        }
        
        /*
         if p.charAt(j) == '*', dp[i][j] = true, if:
         1. match zero element, dp[i][j + 1];
         2. match at least one element, dp[i + 1][j]
         */
        for (int i = slen - 1; i >= 0; i--) {
            for (int j = plen - 1; j >= 0; j--) {
                if (p.charAt(j) != '*') {
                    dp[i][j] = dp[i + 1][j + 1] && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?');
                } else {
                    dp[i][j] = dp[i][j + 1] || dp[i + 1][j];
                }
            }
        }
        return dp[0][0];
    }
}
