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

// a little bit optimize

public class Solution {
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length <= 2) return -1;
        Arrays.sort(nums);
        int n = nums.length;
        int res = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < n; i++) {
            int j = i + 1, k = n - 1;
            if (j >= k) break;
            int predif = Math.abs(nums[i] + nums[j] + nums[k] - target);
            int t = target - nums[i];
            while (j < k) {
                int sum = nums[j] + nums[k];
                if (sum + nums[i] == target) return target;
                if (Math.abs(sum - t) > predif) break;
                if (Math.abs(sum - t) < Math.abs(res - target)) res = sum + nums[i];
                if (sum > t) k--;
                else j++;
            }
        }
        return res;
    }
}
