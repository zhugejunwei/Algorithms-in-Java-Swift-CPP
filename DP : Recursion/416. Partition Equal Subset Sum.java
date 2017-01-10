// dp

public class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0, n = nums.length;
        for (int num : nums) sum += num;
        if ((sum & 1) != 0) return false;
        sum >>>= 1;
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;
        
        // 如果只用一维的数组，那么遍历第二层的时候，需要从后往前，
        // 因为如果从前往后， j - num 可能是现在这个循环刚计算过的值，而不是上一个循环计算的值
        for (int num : nums) {
            for (int j = sum; j >= num; j--) {
                dp[j] = dp[j] || dp[j - num];
            }
        }
        return dp[sum];
    }
}

/// more concise and faster

public class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0, n = nums.length;
        for (int num : nums) sum += num;
        if ((sum & 1) != 0) return false;
        sum >>>= 1;
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;
        int curSum = 0;
        for (int x : nums) {
            int max = Math.min(sum, curSum += x);
            // if j from x -> max; dp[j - x] 是算当前一层的，而本来应该是dp[i - 1][j - x]
            // 为了计算前一层，即 i - 1 层，我们应该从后往前遍历，j: max -> x; 
            for (int j = max; j >= x; j--) {
                dp[j] = dp[j] || dp[j - x];
            }
            if (dp[sum]) return true;
        }
        return dp[sum];
    }
}

// 0-1 knapsack problem
public class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0, n = nums.length;
        for (int num : nums) sum += num;
        if ((sum & 1) != 0) return false;
        sum >>>= 1; // total weight == sum
        int[][] dp = new int[n + 1][sum + 1];
        for (int i = 1; i <= n; i++) {
            int x = nums[i - 1];
            for (int j = 1; j <= sum; j++) {
                if (x > j) dp[i][j] = dp[i - 1][j]; // not use current val
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], x + dp[i - 1][j - x]); // not use or use
                }
                if (dp[i][j] == sum) return true;
            }
        }
        return false;
    }
}
