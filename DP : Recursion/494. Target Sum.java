// 1. very slow, backtracking solution
public class Solution {
    int count = 0;
    public int findTargetSumWays(int[] nums, int S) {
        helper(nums, 0, S, new int[]{1, -1});
        return count;
    }
    
    private void helper(int[] nums, int i, int target, int[] signs) {
        if (i == nums.length) {
            if (target == 0) count++;
            return;
        }
        for (int sign : signs) {
            helper(nums, i + 1, target - nums[i] * sign, signs);
        }
    }
}


// 2. fast DP solution
/*
                   sum(P) - sum(N) = target
 sum(P) + sum(N) + sum(P) - sum(N) = target + sum(P) + sum(N)
                        2 * sum(P) = target + sum(nums)
 
 So the original problem has been converted to a subset sum problem as follows:
 Find a subset P of nums such that sum(P) = (target + sum(nums)) / 2
 */
public class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        // sum1 - sum2 = S
        // sum1 + sum2 + sum1 - sum2 = S + sum_total
        // 2sum1 = S + sum_total
        // sum1 = (S + sum_total)/2
        int sum = 0;
        for (int n : nums) sum += n;
        if ((sum + S) % 2 != 0 || sum < S) return 0;
        sum = (sum + S)/2; // target
        
        int[] dp = new int[sum+1];
        dp[0] = 1;
        for (int n : nums) {
            for (int i = sum; i >= n; i--) {
                dp[i] += dp[i - n];
            }
        }
        return dp[sum];
    }
}
