public class Solution {
    public boolean find132pattern(int[] nums) {
        if (nums == null || nums.length <= 2) return false;
        Deque<Integer> stack = new ArrayDeque();
        int sk = Integer.MIN_VALUE;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] < sk) return true;
            while (!stack.isEmpty() && nums[i] > stack.peek()) {
                sk = stack.pop();
            }
            stack.push(nums[i]);
        }
        return false;
    }
}
