public class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[n - 1];
    }
}


/// another solution

public class Solution {
    public int rob(int[] nums) {
        int no = 0, yes = 0, dno = 0; // double no
        for (int i = 0; i < nums.length; i++) {
            int tmp = yes;
            yes = Math.max(dno, no) + nums[i];
            dno = no;
            no = tmp;
        }
        return Math.max(no, yes);
    }
}


//

public class Solution {
    public int rob(int[] nums) {
        int yes = 0, no = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            int tmp = no;
            no = Math.max(no, yes);
            yes = tmp + nums[i];
        }
        return Math.max(no, yes);
    }
}
