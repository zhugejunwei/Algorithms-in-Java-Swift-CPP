public class Solution {
    public boolean isMatch(String s, String p) {
        // if p.charAt(j) == '.' || p.charAt(j) == s.charAt(i)  :  dp[i][j] = dp[i - 1][j - 1];
        // if p.charAt(j) == '*'  :
        //      1. if p.charAt(j - 1) != s.charAt(i) : dp[i][j] = dp[i][j-2]; // a* counts as zero a
        //      2. if p.charAt(j - 1) == s.charAt(i) || p.charAt(j - 1) == '.' :
        //              dp[i][j] = dp[i - 1][j]  // a* counts as multiple a
        //          or  dp[i][j] = dp[i][j - 2] // a* counts as zero a
        //          or  dp[i][j] = dp[i][j - 1] // a* counts as one a
        
        if (p == null) return s == null;
        int slen = s.length(), plen = p.length();
        
        boolean[][] dp = new boolean[slen + 1][plen + 1];
        dp[0][0] = true;
        for (int i = 1; i <= plen; i++) {
            if (p.charAt(i - 1) == '*' && dp[0][i - 2])
                dp[0][i] = true;
        }
        for (int i = 1; i <= slen; i++) {
            for (int j = 1; j <= plen; j++) {
                if (p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    // j - 1 == '*'
                    if (p.charAt(j - 2) != s.charAt(i - 1) && p.charAt(j - 2) != '.')
                        // j - 2 != i - 1
                        dp[i][j] = dp[i][j - 2];
                    else {
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 1] || dp[i][j - 2];
                    }
                }
            }
        }
        return dp[slen][plen];
    }
}

// ================================== Recursion ====================================//

public class Solution {
    public boolean isMatch(String s, String p) {
        for (int i = 0; i < p.length(); s = s.substring(1)) {
            char c = p.charAt(i);
            if (i + 1 >= p.length() || p.charAt(i + 1) != '*') {
                // p[next char] != '*'
                i++;
            } else if (isMatch(s, p.substring(i + 2))) {
                // p[next char] == '*'
                return true;
            }
            
            if (s.isEmpty() || (c != '.' && c != s.charAt(0)))
                // p[i] != '.' && p[i] != s[0]
                return false;
        }
        return s.isEmpty();
    }
}
