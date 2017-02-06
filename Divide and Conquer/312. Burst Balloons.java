// top -> down, this is faster

public class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        int[] bal = new int[n + 2];
        for (int i = 1; i <= n; i++) {
            bal[i] = nums[i - 1];
        }
        bal[0] = bal[n + 1] = 1;
        return helper(new int[n + 1][n + 1], bal, 1, n);
    }
    
    private int helper(int[][] cache, int[] bal, int l, int r) {
        if (l > r) return 0;
        if (cache[l][r] != 0) return cache[l][r];
        int res = 0;
        for (int i = l; i <= r; i++) {
            int left = helper(cache, bal, l, i - 1);
            int right = helper(cache, bal, i + 1, r);
            int burst = bal[l - 1] * bal[i] * bal[r + 1];  // last burst !!
            res = Math.max(res, left + burst + right);
        }
        cache[l][r] = res;
        return res;
    }
}
// dp
// bot -> up

public class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n + 2][n + 2];
        int[] bal = new int[n + 2];
        for (int i = 1; i <= n; i++) {
            bal[i] = nums[i - 1];
        }
        bal[0] = bal[n + 1] = 1;
        for (int len = 1; len <= n; len++) {
            for (int l = 1; l + len - 1 <= n; l++) {
                int r = l + len - 1;
                for (int k = l; k <= r; k++) {
                    int left = 0, right = 0;
                    if (k > l) left = dp[l][k - 1];
                    if (k < r) right = dp[k + 1][r];
                    dp[l][r] = Math.max(dp[l][r], bal[l - 1] * bal[k] * bal[r + 1] + left + right);
                }
            }
        }
        return dp[1][n];
    }
}
