public class Solution {
    int[] output;
    int[] nums;
    Random rand;
    
    public Solution(int[] nums) {
        this.nums = nums;
        this.output = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            output[i] = nums[i];
        }
        rand = new Random();
        
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return output;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        for (int i = nums.length - 1; i > 0; i--) {
            int index = rand.nextInt(i + 1);
            if (index != i) {
                nums[index] ^= nums[i];
                nums[i] ^= nums[index];
                nums[index] ^= nums[i];
            }
        }
        return nums;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */
