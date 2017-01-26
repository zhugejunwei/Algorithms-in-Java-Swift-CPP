public class Solution {
    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        
        int i = 1;
        for (i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) return i + 1;
        }
        return nums.length + 1;
    }
    
    private void swap(int[] nums, int i, int j) {
        nums[i] ^= nums[j];
        nums[j] ^= nums[i];
        nums[i] ^= nums[j];
    }
}

// 类似于 quick selection 
public class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        if (n == 0) return 1;
        int k = partition(nums, 0, n - 1); // k == the count of positive numbers at the left side
        int tmp = 0;
        int res = k;
        for (int i = 0; i < k; i++) {
            tmp = Math.abs(nums[i]);
            if (tmp <= k) {
                nums[tmp - 1] = nums[tmp - 1] < 0 ? nums[tmp - 1] : -nums[tmp - 1];
            }
        }
        
        for (int i = 0; i < k; i++) {
            if (nums[i] > 0) {
                res = i;
                break;
            }
        }
        
        return res + 1;
    }
    
    private int partition(int[] nums, int l, int r) {
        int i = -1;
        for (int j = l; j <= r; j++) {
            if (nums[j] > 0) {
                i++;
                swap(nums, i, j);
            }
        }
        return i + 1;
    }
    
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
