public class Solution {
    public int missingNumber(int[] nums) {
        int res = nums.length;
        for (int i = 0; i < nums.length; i++) {
            // i:[0, n - 1]; nums[i]:[0,n]; res:n
            res ^= i;
            res ^= nums[i];
        }
        return res;
    }
}

// use sum

public class Solution {
    public int missingNumber(int[] nums) {
        int sum = (1 + nums.length) * nums.length / 2;
        for (int n : nums) {
            sum -= n;
        }
        return sum;
    }
}
