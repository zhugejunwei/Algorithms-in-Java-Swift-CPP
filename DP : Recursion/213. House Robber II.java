public class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        return Math.max(rob(nums, 0, n-2), rob(nums, 1, n-1));
    }
    private int rob(int[] nums, int l, int r) {
        int yes = 0, no = 0, n = nums.length;
        for (int i = l; i <= r; i++) {
            int tmp = no;
            no = Math.max(no, yes);
            yes = tmp + nums[i];
        }
        return Math.max(no, yes);
    }
}
