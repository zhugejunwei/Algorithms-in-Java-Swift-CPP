// fast O(n) solution
public class Solution {
    public boolean find132pattern(int[] nums) {
        if (nums == null || nums.length <= 2) return false;
        Deque<Integer> stack = new ArrayDeque();
        int sk = Integer.MIN_VALUE;
        for (int i = nums.length - 1; i >= 0; i--) {
            // check wether there is a smallest value si < sk
            if (nums[i] < sk) return true;
            // sk is a value that smaller than some value(sj)
            while (!stack.isEmpty() && nums[i] > stack.peek()) {
                sk = stack.pop();
            }
            stack.push(nums[i]);
        }
        return false;
    }
}


// slower n^2 solution
public class Solution {
    public boolean find132pattern(int[] nums) {
        if (nums == null || nums.length <= 2) return false;
        
        for (int i = 0; i < nums.length; i++) {
            int k = nums.length - 1;
            // find nk that is bigger than ni
            while (nums[k] <= nums[i] && k > i + 1) k--;
            int nk = nums[k];
            // find some value that is bigger than nk, update uk if possible
            for (int j = k - 1; j > i; j--) {
                if (nums[j] > nums[i] && nums[j] < nk) {
                    nk = nums[j];
                }
                if (nums[j] > nk) return true;
            }
        }
        return false;
    }
}
