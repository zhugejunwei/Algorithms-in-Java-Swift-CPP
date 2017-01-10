public class Solution {
    public int numDistinct(String s, String t) {
        int m = t.length(), n = s.length();
        int[][] dp = new int[m + 1][n + 1];
        
        for (int j = 0; j <= n; ++j) dp[0][j] = 1;
        for (int i = 1; i <= m; ++i) dp[i][0] = 0;
        
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                dp[i][j] = (s.charAt(j - 1) != t.charAt(i - 1)) ? dp[i][j-1] : (dp[i-1][j-1] + dp[i][j-1]);
            }
        }
        return dp[m][n];
    }
}

// excellent explanation

/**
 * Solution (DP):
 * We keep a m*n matrix and scanning through string S, while
 * m = T.length() + 1 and n = S.length() + 1
 * and each cell in matrix Path[i][j] means the number of distinct subsequences of
 * T.substr(1...i) in S(1...j)
 *
 * Path[i][j] = Path[i][j-1]            (discard S[j])
 *              +     Path[i-1][j-1]    (S[j] == T[i] and we are going to use S[j])
 *                 or 0                 (S[j] != T[i] so we could not use S[j])
 * while Path[0][j] = 1 and Path[i][0] = 0.
 */
public class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int j = 0; j <= m; j++) dp[0][j] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = dp[i][j - 1] + ((s.charAt(j - 1) != t.charAt(i - 1)) ? 0 : dp[i - 1][j - 1]);
            }
        }
        return dp[n][m];
    }
}
