public class Solution {
    public boolean makesquare(int[] nums) {
        int sum = 0, n = nums.length;
        for (int num : nums) sum += num;
        if (sum == 0 || n == 0 || (sum & 0b11) != 0) return false;
        sum >>>= 2;
        boolean[] dp = new boolean[1 << n];
        List<Integer> parts = new ArrayList();
        int all = (1 << n) - 1;
        for (int mask = 0; mask <= all; mask++) {
            int tmp = 0;
            for (int i = 0; i < n; i++) {
                if (((mask >>> i) & 1) == 1)
                    tmp += nums[i];
            }
            if (tmp == sum) {
                for (int x : parts) {
                    if ((mask & x) == 0) {
                        dp[mask | x] = true;
                        if (dp[all ^ (mask | x)]) {
                            return true;
                        }
                    }
                }
                parts.add(mask);
            }
        }
        return false;
    }
}
