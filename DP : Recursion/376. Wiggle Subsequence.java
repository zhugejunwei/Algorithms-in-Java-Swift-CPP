public class Solution {
    public int wiggleMaxLength(int[] nums) {
        if (nums.length < 2) return nums.length;
        int b = 1;
        int s = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) b = s + 1;
            if (nums[i] < nums[i - 1]) s = b + 1;
        }
        return Math.max(s, b);
    }
}

// another good solution
public class Solution {
    public int wiggleMaxLength(int[] nums) {
        int len = nums.length;
        if (len < 2) return len;
        int k = 1;
        while (k < len && nums[k] == nums[k - 1]) k++;
        if (k == len) return 1;
        int max = 2;
        boolean bigger = (nums[k] > nums[k - 1]);
        for (int i = k + 1; i < len; i++) {
            if (bigger && nums[i] < nums[i-1]) {
                max++;
                bigger = false;
            } else if (!bigger && nums[i] > nums[i-1]) {
                max++;
                bigger = true;
            }
        }
        return max;
    }
}
