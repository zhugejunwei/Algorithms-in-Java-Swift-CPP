public class Solution {
    public int uniquePathsWithObstacles(int[][] b) {
        if (b == null || b.length == 0 || b[0][0] == 1) return 0;
        int width = b[0].length;
        int[] dp = new int[width];
        dp[0] = 1;
        for (int[] row : b) {
            for (int j = 0; j < width; j++) {
                if (row[j] == 1) dp[j] = 0;
                else if (j > 0) dp[j] += dp[j - 1];
            }
        }
        return dp[width - 1];
    }
}
