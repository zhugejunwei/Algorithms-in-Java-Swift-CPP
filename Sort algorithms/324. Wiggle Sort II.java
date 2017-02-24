// three-way partitioning:
// https://en.wikipedia.org/wiki/Dutch_national_flag_problem#Pseudocode

// explanation of this solution, how virtual index works:
// https://discuss.leetcode.com/topic/32861/3-lines-python-with-explanation-proof/2
public class Solution {
    public void wiggleSort(int[] nums) {
        int n = nums.length;
        int median = findMedian(nums, (n + 1)/2);
        
        int i = 0, l = 0, r = n - 1;
        while (i <= r) {
            if (nums[newIdx(i, n)] > median) {
                swap(nums, newIdx(i++, n), newIdx(l++, n));
            } else if (nums[newIdx(i, n)] < median) {
                swap(nums, newIdx(r--, n), newIdx(i, n));
            } else i++;
        }
    }
    
    private int newIdx(int i, int n) {
        return (1 + 2 * i) % (n | 1);
    }
    
    private int findMedian(int[] nums, int k) {
        int l = 0, r = nums.length - 1;
        k = nums.length - k;
        while (l < r) {
            int p = partition(nums, l, r);
            if (p > k) {
                r = p - 1;
            } else if (p < k) {
                l = p + 1;
            } else break;
        }
        return nums[k];
    }
    
    private int partition(int[] nums, int l, int r) {
        int i = l;
        for (int j = l; j < r; j++) {
            if (nums[j] > nums[r]) {
                swap(nums, i, j);
                i++;
            }
        }
        swap(nums, i, r);
        return i;
    }
    
    private void swap(int[] nums, int i, int j) {
        if (nums[i] == nums[j]) return;
        nums[i] ^= nums[j];
        nums[j] ^= nums[i];
        nums[i] ^= nums[j];
    }
}
