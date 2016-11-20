public class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int n : nums) sum += n;
        if ((sum & 1) != 0) return false;
        sum >>>= 1;
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;
        for (int i = 1; i <= nums.length; i++) {
            for (int j = sum; j >= nums[i-1]; j--) {
                dp[j] = dp[j] || dp[j - nums[i-1]];
            }
        }
        return dp[sum];
    }
}

// bitset solution
