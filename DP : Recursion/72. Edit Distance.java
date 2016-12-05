public class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        if (m == 0) return n;
        if (n == 0) return m;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1];
                else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1] + 1, dp[i - 1][j] + 1), dp[i][j - 1] + 1);
                }
            }
        }
        return dp[m][n];
    }
}

/*
 分成2个case：
 1. boundary case， 2. general case
 
 -> i == 0, j != 0 的时候，先解决boundary case（比如empty string，dp[i][0]），dp[i][0] = what, or dp[0][j] = what
 然后是一般的情况（非空string），convert to smaller problems：
 -> 已知how to convert word1[0...i-2] to word2[0...j-2], which is dp[i-1][j-1]，那么如何求 word1[i - 1] to word2[j-1]呢？
 
	0. word1[i - 1] == word2[j-1], 那么dp[i][j] = dp[i-1][j-1]
	1. replace: 将word1[i-1]替代成word2[j-1], dp[i][j] = dp[i-1][j-1] + 1
	2. delete: 将word1[i-1] 删除，word1[0...i-2] == word2[0...j-1], dp[i][j] = dp[i-1][j] + 1
	3. insert: 在word1[i-1]后增加word2[j-1], 使得word1[0...i-1] + word2[j-1] == word2[0...j-1]，两边同时删除word2[j-1]，所以word1[0...i-1]  == word2[j-2]，所以 dp[i][j] = dp[i][j－1]。
 
 将上面的各种情况放一起：
 dp[i][0] = i;
 dp[0][j] = j;
 dp[i][j] = dp[i-1][j-1] (equal)
 dp[i][j] = Math.min(
	dp[i-1][j-1] + 1 (replace)
	dp[i-1][j] + 1 (delete)
	dp[i][j-1] + 1 (insert)
	)
*/
