public class Solution {
    public int splitArray(int[] nums, int m) {
        int l = 0, r = 0; // l = largest valie in nums; r = sum of all value in nums
        for (int n : nums) {
            if (n > l) l = n;
            r += n;
        }
        
        while (l < r) {
            int mid = (l & r) + ((l ^ r) >>> 1);
            if (noLargerThanM(nums, mid, m)) {
                // if the number of subarrays is not larger than m, mid could be smaller
                r = mid;
            } else {
                // if the number of subarrays is larger than m, mid should be larger.
                l = mid + 1;
            }
        }
        return l;
    }
    
    public boolean noLargerThanM(int[] nums, int target, int m) {
        int sum = 0, count = 1;
        for (int n : nums) {
            // here it is actually not from left to right, the order doesn't matter, it is easy to prove:
            // if a + b > t, c + d > t
            // then there is no way to put a + b + c + d in one array, that is to say,
            // it could be a + b + c > t, d > t
            // or a > t, b + c + d > t
            // it doesn't matter, the number of subarrays is still 2, which is equal to a+b>t, and c+d > t
            sum += n;
            if (sum > target) {
                // find a way to form subarray, as said before, it doesn't matter how to form the subarray
                // the number matters, which will not change according to the ways to form a subarray.
                sum = n;
                count++;
            }
            if (count > m) return false; // if count == m, we still have a chance to make mid smaller
        }
        return true;
    }
}



// DP solution
// http://articles.leetcode.com/the-painters-partition-problem/
public class Solution {
    public int splitArray(int[] nums, int k) {
        int n = nums.length;
        int[][] dp = new int[n + 1][k + 1];
        long[] sum = new long[n + 1];
        
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        
        // if there is only one painter
        for (int i = 1; i <= n; i++) dp[i][1] = sum[i] > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int)sum[i];
        // if there is only one block
        for (int j = 1; j <= k; j++) dp[1][j] = nums[0];
        
        for (int i = 2; i <= k; i++) {
            for (int j = 2; j <= n; j++) {
                int best = Integer.MAX_VALUE;
                for (int p = 1; p <= j; p++) {
                    /*
                                      n              n-1
                     M[n, k] = min { max { M[j, k-1], ∑ Ai } }
                                     j=1             i=j
                     */
                    best = (int)Math.min(best, Math.max(dp[p][i-1], sum[j] - sum[p]));
                }
                dp[j][i] = best;
            }
        }
        return dp[n][k];
    }
}
