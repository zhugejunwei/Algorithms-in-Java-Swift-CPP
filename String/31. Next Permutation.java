/*
 1. Find the largest index k such that nums[k] < nums[k + 1]. If no such index exists, the permutation is sorted in descending order, just reverse it to ascending order and we are done. For example, the next permutation of [3, 2, 1] is [1, 2, 3].
 2. Find the largest index l greater than k such that nums[k] < nums[l].
 3. Swap the value of nums[k] with that of nums[l].
 4. Reverse the sequence from nums[k + 1] up to and including the final element nums[nums.size() - 1].
 */
public class Solution {
    public void nextPermutation(int[] nums) {
        int k = nums.length - 1;
        // find largest k such that nums[k] < nums[k + 1]
        while (k > 0 && nums[k] <= nums[k - 1]) {
            k--;
        }
        // if the permutation is sorted in descending order
        if (--k < 0) {
            reverse(nums, 0, nums.length - 1);
            return;
        }
        // find largest j such that nums[j] > nums[k]
        int j = nums.length - 1;
        while (j > k && nums[j] <= nums[k]) {
            j--;
        }
        // swap with k and j
        nums[j] ^= nums[k];
        nums[k] ^= nums[j];
        nums[j] ^= nums[k];
        // reverse from k + 1 to the final
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
