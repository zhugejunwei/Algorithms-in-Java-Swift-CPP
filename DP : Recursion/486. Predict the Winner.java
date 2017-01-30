// DP, similar with burst balloon
public class Solution {
    public boolean PredictTheWinner(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) dp[i][i] = nums[i];
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }
        return dp[0][n - 1] >= 0;
    }
}

// p1 选左边或者选右边，有一边赢就行，
// p2 选左边或者选右边，p1都必须赢，要不然p2就会选p1会输的那一边
public class Solution {
    public boolean PredictTheWinner(int[] nums) {
        return helper(0, 0, nums, 0, nums.length - 1, true);
    }
    
    private boolean helper(int p1, int p2, int[] nums, int i, int j, boolean isP1) {
        if (i > j) return p1 >= p2;
        if (isP1) {
            return helper(p1 + nums[i], p2, nums, i + 1, j, !isP1)
            || helper(p1 + nums[j], p2, nums, i, j - 1, !isP1);
        } else {
            return helper(p1, p2 + nums[i], nums, i + 1, j, !isP1)
            && helper(p1, p2 + nums[j], nums, i, j - 1, !isP1);
        }
    }
}

// another solution
// 下一个人必须左边或者右边都是赢的，这个人才会输，要不然这个人会先选一个让对手输的。

public class Solution {
    // if p1 is winner,
    // the max number of p2 <= the max number of p1
    public boolean PredictTheWinner(int[] nums) {
        int total = 0;
        for (int num : nums) total += num;
        return first(0, 0, total, nums, 0, nums.length - 1);
    }
    
    // p1 win,
    private boolean first(int p1, int p2, int total, int[] nums, int i, int j) {
        if (i >= j) {
            return p1 >= p2;
        }
        if (p1*2 >= total) return true;
        return !(second(p1 + nums[i], p2, total, nums, i + 1, j) && second(p1 + nums[j], p2, total, nums, i, j - 1));
    }
    
    // p2 win
    private boolean second(int p1, int p2, int total, int[] nums, int i, int j) {
        if (i >= j) {
            return p1 < p2;
        }
        if (p2 * 2 > total) return true;
        return !(first(p1, p2 + nums[i], total, nums, i + 1, j) && first(p1, p2 + nums[j], total, nums, i, j - 1));
    }
}

// DP
/*
 p1 picks nums[i], p2 picks max from nums[i+1]~nums[j], p1 picks the minimum number that p2 left after picks from nums[i+1]~nums[j], that is, min(helper(nums, i+1, j-1), helper(nums, i+2,j))
 */
public class Solution {
    public boolean PredictTheWinner(int[] nums) {
        int n = nums.length;
        if (n % 2 == 0) return true;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(dp[i], -1);
        int val = helper(nums, dp, 0, n - 1);
        return val * 2 >= IntStream.of(nums).sum();
    }
    
    private int helper(int[] nums, int[][] dp, int i, int j) {
        if (i > j) return 0;
        if (dp[i][j] != -1) return dp[i][j];
        int left = nums[i] + Math.min(helper(nums, dp, i + 2, j), helper(nums, dp, i + 1, j - 1));
        int right = nums[j] + Math.min(helper(nums, dp, i + 1, j - 1), helper(nums, dp, i, j - 2));
        dp[i][j] = Math.max(left, right);
        return dp[i][j];
    }
}

/*
 Either the first player picks a[0] and second player must gain at least a[0] more than the first player in the subproblem s[1...n], or , first player picks a[n] and second player must gain at least a[n] more than the first player in the subproblem s[0..n-1]. If in either case the second player cannot achieve this then first player wins.
 */

public class Solution {
    public boolean PredictTheWinner(int[] nums) {
        return maxp1(nums, 0, nums.length - 1) >= 0;
    }
    
    private int maxp1(int[] nums, int i, int j) {
        return i == j ? nums[i] : Math.max(nums[i]-maxp1(nums,i+1,j), nums[j]-maxp1(nums,i,j-1));
    }
}
