public class Solution {
    public int findKthLargest(int[] nums, int k) {
        shuffle(nums);
        k = nums.length - k;
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int p = partition(nums, l, r);
            if (p < k) {
                l = p + 1;
            } else if (p > k) {
                r = p - 1;
            } else break;
        }
        return nums[k];
    }
    
    private int partition(int[] nums, int l, int r) {
        int i = l;
        for (int j = l; j < r; j++) {
            if (nums[j] < nums[r]) {
                if (nums[i] != nums[j]) swap(nums, i, j);
                i++;
            }
        }
        if (nums[i] != nums[r]) swap(nums, i, r);
        return i;
    }
    
    private void shuffle(int[] nums) {
        Random rand = new Random();
        for (int i = 1; i < nums.length; i++) {
            int r = rand.nextInt(i + 1);
            if (nums[i] != nums[r]) swap(nums, i, r);
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        nums[i] ^= nums[j];
        nums[j] ^= nums[i];
        nums[i] ^= nums[j];
    }
}
