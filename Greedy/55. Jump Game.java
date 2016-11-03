public class Solution {
    public boolean canJump(int[] nums) {
        return canJump(nums, nums.length);
    }
    
    private boolean canJump(int[] nums, int n) {
        int i = 0;
        // i <= reach, the furthest "start point" that I can reach
        // start point + nums[i] >= n, win!
        // start point + nums[i] < n, lose!
        // so greedy
        for (int reach = 0; i < n && i <= reach; ++i)
            reach = Math.max(i + nums[i], reach);
        return i == n;
    }
}
