// Recursion

public class Solution {
    public boolean isMatch(String s, String p) {
        if (p.length() == 0) return s.length() == 0;
        if (p.length() == 1) return s.length() == 1 && (p.charAt(0) == '.' || p.charAt(0) == s.charAt(0));
        if (p.length() > 1 && p.charAt(1) == '*') {
            return isMatch(s, p.substring(2)) || (s.length() != 0 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')) &&isMatch(s.substring(1), p);
        } else {
            if (s.length() == 0) return false;
            return (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.') ? isMatch(s.substring(1), p.substring(1)) : false;
        }
    }
}

// DP

public class Solution {
    public boolean isMatch(String s, String p) {
        int slen = s.length(), plen = p.length();
        boolean[][] dp = new boolean[slen + 1][plen + 1];
        dp[0][0] = true;
        
        // matches the empty string
        for (int j = 1; j <= plen; j++) {
            dp[0][j] = j > 1 && dp[0][j - 2] && p.charAt(j - 1) == '*';
        }
        
        /* When p[j - 1] == '*', dp[i][j] = true
         if:
         1. match zero element, dp[i][j - 2]
         2 . match at least one element, dp[i - 1][j] && s[i - 1] matches p[j - 2]
         */
        for (int i = 1; i <= slen; i++) {
            for (int j = 1; j <= plen; j++) {
                if (p.charAt(j - 1) != '*') {
                    dp[i][j] = dp[i - 1][j - 1] && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.');
                } else {
                    dp[i][j] = dp[i][j - 2] || (dp[i - 1][j] && (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.'));
                }
            }
        }
        return dp[slen][plen];
    }
}
