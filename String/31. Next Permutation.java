public class Solution {
    public void nextPermutation(int[] nums) {
        int k = nums.length - 1;
        while (k > 0 && nums[k] <= nums[k - 1]) {
            k--;
        }
        if (--k < 0) {
            reverse(nums, 0, nums.length - 1);
            return;
        }
        int j = nums.length - 1;
        while (j > k && nums[j] <= nums[k]) {
            j--;
        }
        nums[j] ^= nums[k];
        nums[k] ^= nums[j];
        nums[j] ^= nums[k];
        reverse(nums, k + 1, nums.length - 1);
    }
    
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            nums[start] ^= nums[end];
            nums[end] ^= nums[start];
            nums[start] ^= nums[end];
            start++;
            end--;
        }
    }
}
