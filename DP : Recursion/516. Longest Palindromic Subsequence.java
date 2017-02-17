// recursion + memo
// time complexity: O(n^2), because to fill the memo 2d matrix(top-right half matrix) needs O(n^2) time, for each cell, it's O(1) operation.
public class Solution {
    public int longestPalindromeSubseq(String s) {
        int[][] memo = new int[s.length()][s.length()];
        return helper(s, memo, 0, s.length() - 1);
    }
    
    private int helper(String s, int[][] memo, int i, int j) {
        if (memo[i][j] != 0) return memo[i][j];
        if (i > j) return 0;
        if (i == j) return 1;
        if (s.charAt(i) == s.charAt(j)) {
            memo[i][j] = helper(s, memo, i + 1, j - 1) + 2;
        } else {
            memo[i][j] = Math.max(helper(s, memo, i + 1, j), helper(s, memo, i, j - 1));
        }
        return memo[i][j];
    }
}

// DP solution, left -> right
public class Solution {
    // left -> right
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int j = 0; j < n; j++) {
            dp[j][j] = 1;
            for (int i = j - 1; i >= 0; i--) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        return dp[0][n-1];
    }
}

// DP, right -> left
public class Solution {
    // right -> left
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = n-1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        return dp[0][n-1];
    }
}

// DP, O(n) space
public class Solution {
    // right -> left, O(n) space
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = n-1; i >= 0; i--) {
            int tmp = 0, tmp_last = 0;
            for (int j = i + 1; j < n; j++) {
                tmp = dp[j]; // next dp[i+1][j-1]
                if (s.charAt(i) == s.charAt(j)) {
                    dp[j] = tmp_last + 2;
                } else {
                    dp[j] = Math.max(dp[j], dp[j-1]);
                }
                tmp_last = tmp;
            }
        }
        return dp[n-1];
    }
}

// use reversed s, compute the longest common subsequence of s and reversed_s
public class Solution {
    // right -> left, O(n) space
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        int[][] dp = new int[n + 1][n + 1];
        String rev = new StringBuilder(s).reverse().toString();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == rev.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n][n];
    }
}
