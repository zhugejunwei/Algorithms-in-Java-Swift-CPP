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
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int n : nums) sum += n;
        if (target > sum || target < -sum || ((target + sum) & 1) != 0)
            return 0;
        return  subsetSum(nums, (sum + target) >>> 1);
    }
    
    private int subsetSum(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int num : nums) {
            for (int j = target; j >= num; j--) {
                dp[j] += dp[j - num];
            }
        }
        return dp[target];
    }
}

