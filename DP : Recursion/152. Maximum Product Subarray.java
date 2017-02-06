// two side dp

public class Solution {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int tmp = nums[0];
        int max = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i - 1] == 0) tmp = nums[i];
            else tmp *= nums[i];
            if (tmp > max) max = tmp;
        }
        tmp = nums[n - 1];
        max = Math.max(max, tmp);
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i + 1] == 0) tmp = nums[i];
            else tmp *= nums[i];
            if (tmp > max) max = tmp;
        }
        return max;
    }
}


// another dp

public class Solution {
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE;
        int curMin = 1, curMax = 1;
        for (int n : nums) {
            int p1 = curMin * n, p2 = curMax * n;
            curMin = Math.min(n, Math.min(p1, p2));
            curMax = Math.max(n, Math.max(p1, p2));
            if (curMax > max) max = curMax;
        }
        return max;
    }
}
