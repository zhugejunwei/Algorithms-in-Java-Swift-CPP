public class Solution {
    public int splitArray(int[] nums, int m) {
        int n = nums.length;
        int[] dp = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = dp[i + 1] + nums[i];
        }
        
        for (int k = 2; k <= m; k++) {
            int last = n-(k-1);
            for (int i = 0; i < last; i++) {
                int leftSum = 0;
                dp[i] = Integer.MAX_VALUE;
                for (int p = i; p < last; p++) {
                    leftSum += nums[p];
                    if (leftSum > dp[i]) break;
                    // max sum
                    int tmpMax = Math.max(leftSum, dp[p + 1]);
                    // minimum max sum
                    if (tmpMax < dp[i]) {
                        dp[i] = tmpMax;
                    }
                }
                // last round, only need to compute i=0
                if (k == m) break;
            }
        }
        return dp[0];
    }
}

// 2D
public class Solution {
    public int splitArray(int[] nums, int k) {
        int n = nums.length;
        int[][] dp = new int[n + 1][k + 1];
        long[] sum = new long[n + 1];
        
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        
        for (int i = 1; i <= n; i++) dp[i][1] = sum[i] > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int)sum[i];
        for (int j = 1; j <= k; j++) dp[1][j] = nums[0];
        
        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= k; j++) {
                int best = Integer.MAX_VALUE;
                for (int p = 1; p <= i; p++) {
                    best = (int)Math.min(best, Math.max(dp[p][j - 1], sum[i] - sum[p]));
                }
                dp[i][j] = best;
            }
        }
        // or in another way below, the same
//        for (int i = 2; i <= k; i++) {
//            for (int j = 2; j <= n; j++) {
//                int best = Integer.MAX_VALUE;
//                for (int p = 1; p <= j; p++) {
//                    best = (int)Math.min(best, Math.max(dp[p][i-1], sum[j] - sum[p]));
//                }
//                dp[j][i] = best;
//            }
//        }
        return dp[n][k];
    }
}
