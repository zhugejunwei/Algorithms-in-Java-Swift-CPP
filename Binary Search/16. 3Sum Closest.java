public class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int n = nums.length, res = 0;
        if (n <= 3) {
            for (int i = 0; i < n; i++) res += nums[i];
            return res;
        }
        Arrays.sort(nums);
        res = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < n; i++) {
            int j = i + 1, k = n - 1;
            int tmp = 0;
            while (j < k) {
                tmp = nums[i] + nums[j] + nums[k];
                if (Math.abs(tmp - target) < Math.abs(res - target)) {
                    res = tmp;
                    if (res == target) return res;
                }
                if (tmp > target) k--;
                else j++;
            }
        }
        return res;
    }
}
