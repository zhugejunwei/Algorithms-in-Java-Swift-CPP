public class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        for (int start = 0; k != 0 && start < nums.length; n -= k, start += k, k %= n) {
            for (int i = 0; i < k; i++) {
                swap(nums, start + i, nums.length - k + i);
            }
        }
    }
    
    private static void swap(int[] nums, int a, int b){
        int temp = nums[b];
        nums[b]= nums[a];
        nums[a]=temp;
    }
}

//
