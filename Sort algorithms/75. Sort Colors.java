public class Solution {
    public void sortColors(int[] nums) {
        int n = nums.length;
        int zero = 0, two = n - 1;
        for (int i = 0; i <= two; i++) {
            while (nums[i] == 2 && i < two) swap(nums, i, two--);
            while (nums[i] == 0 && i > zero) swap(nums, i, zero++);
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}

// another solution: 3-way partition
public class Solution {
    public void sortColors(int[] nums) {
        int l = 0, i = 0, r = nums.length - 1, mid = 1;
        while (i <= r) {
            if (nums[i] < mid) {
                swap(nums, i++, l++);
            } else if (nums[i] > mid) {
                swap(nums, i, r--);
            } else i++;
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
