public class Solution {
    public int search(int[] nums, int target) {
        if (nums.length == 0) return -1;
        int len = nums.length;
        int largest = Integer.MIN_VALUE, m = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] > largest) {
                largest = nums[i];
                m = i;
            }
        }
        int i = 0, j = len - 1, res = -1;
        if (target >= nums[0]) {
            res = find(nums, target, i, m);
        } else {
            res = find(nums, target, m + 1, j);
        }
        return res;
    }
    
    private int find(int[] nums, int target, int i, int j) {
        while (i <= j) {
            int m = i + (j - i) / 2;
            if (nums[m] > target) {
                j = m - 1;
            } else if (nums[m] < target) {
                i = m + 1;
            } else {
                return m;
            }
        }
        return -1;
    }
}

// one time bs
public class Solution {
    public int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end){
            int mid = (start + end) / 2;
            if (nums[mid] == target)
                return mid;
            
            if (nums[start] <= nums[mid]){
                if (target < nums[mid] && target >= nums[start])
                    end = mid - 1;
                else
                    start = mid + 1;
            }
            
            if (nums[mid] <= nums[end]){
                if (target > nums[mid] && target <= nums[end])
                    start = mid + 1;
                else
                    end = mid - 1;
            }
        }
        return -1;
    }
}
