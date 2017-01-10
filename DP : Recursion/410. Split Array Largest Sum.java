public class Solution {
    public int splitArray(int[] nums, int m) {
        int n = nums.length;
        int[] dp = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = dp[i + 1] + nums[i];
        }
        
        // for #sub: 2 -> m
        // for start point(i): 0 -> n - sub
        // 从i开始，将nums[i..<n+1-sub]分成sub份
        // 从i+1开始，把后面的分成s份是为了后面计算分成s+1份做准备，
        // 即从i开始的s+1份＝从nums[i]这份 和 i＋1开始的s份
        for (int s = 2; s <= m; s++) {
            int rightMost = n + 1 - s;
            for (int i = 0; i < rightMost; i++) {
                int leftSum = 0;
                dp[i] = Integer.MAX_VALUE; // avoid this case : [1,2147483647]
                for (int j = i; j < rightMost; j++) {
                    leftSum += nums[j];
                    if (leftSum > dp[i]) break;
                    int largest = Math.max(leftSum, dp[j + 1]); // leftSum = first part sum, dp[j + 1] = other s-1 parts sum
                    if (largest < dp[i]) {
                        dp[i] = largest;
                    }
                }
                if (s == m) break; // if it's the last round, just need to calculate the start point == 0
            }
        }
        return dp[0];
    }
}
