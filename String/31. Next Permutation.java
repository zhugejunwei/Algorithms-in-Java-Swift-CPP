/*
 1. Find the largest index k such that nums[k] < nums[k + 1]. If no such index exists, the permutation is sorted in descending order, just reverse it to ascending order and we are done. For example, the next permutation of [3, 2, 1] is [1, 2, 3].
 2. Find the largest index l greater than k such that nums[k] < nums[l].
 3. Swap the value of nums[k] with that of nums[l].
 4. Reverse the sequence from nums[k + 1] up to and including the final element nums[nums.size() - 1].
 */
public class Solution {
    public void nextPermutation(int[] nums) {
        int k = nums.length - 2;
        
        // 1. find the last element in nums, nums[k] < nums[k + 1]
        while (k >= 0 && nums[k + 1] <= nums[k]) {
            k--;
        }
        // if the permutation is sorted in descending order
        if (k < 0) {
            reverse(nums, 0, nums.length - 1);
            return;
        }
        // got nums[k] < nums[k + 1]
        
        // 2. l > k, nums[l] > nums[k]
        int l = nums.length - 1;
        while (l > k && nums[l] <= nums[k]) {
            l--;
        }
        // get nums[k] < nums[l]
        
        // 3. swap nums[k], nums[l]
        swap(nums, k, l);
        
        // 4. k + 1 ... end
        reverse(nums, k + 1, nums.length - 1);
    }
    
    private void swap(int[] nums, int l, int r) {
        int tmp = nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }
    
    private void reverse(int[] nums, int l, int r) {
        while (l < r) {
            swap(nums, l++, r--);
        }
    }
}
