public class Solution {
    public boolean circularArrayLoop(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) continue;
            
            // slow and fast to check cycle
            int slow = i, fast = getIdx(i, nums);
            // using nums[fast] * nums[i] > 0 to check it's on a fixed direction: The loop must be "forward" or "backward'.
            while (nums[fast] * nums[i] > 0 && nums[slow] * nums[i] > 0) {
                if (fast == slow) {
                    if (slow == getIdx(slow, nums)) break;
                    return true;
                }
                slow = getIdx(slow, nums);
                fast = getIdx(getIdx(fast, nums), nums);
            }
            
            // flip the checked path into 0
            slow = i;
            while (nums[slow] * nums[i] > 0) {
                nums[slow] = 0;
                slow = getIdx(slow, nums);
            }
        }
        return false;
    }
    
    private int getIdx(int i, int[] nums) {
        int n = nums.length;
        return (n + i + nums[i]) % n;
    }
}
