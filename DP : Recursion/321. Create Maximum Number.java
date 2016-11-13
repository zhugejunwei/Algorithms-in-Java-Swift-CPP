public class Solution {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int n1 = nums1.length, n2 = nums2.length;
        int[] res = new int[k];
        for (int i = Math.max(0, k - n2); i <= n1 && i <= k; i++) {
            int[] candidate = merge(maxArray(nums1, i), maxArray(nums2, k - i), k);
            if (greater(candidate, 0, res, 0)) res = candidate;
        }
        return res;
    }
    private int[] maxArray(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[k];
        for (int i = 0, j = 0; i < n; i++) {
            while (n - i > k - j && j > 0 && nums[i] > res[j - 1]) j--;
            if (j < k) res[j++] = nums[i];
        }
        return res;
    }
    private int[] merge(int[] nums1, int[] nums2, int k) {
        int[] res = new int[k];
        int n1 = nums1.length, n2 = nums2.length;
        for (int i = 0, j = 0, r = 0; r < k; ++r) {
            res[r] = greater(nums1, i, nums2, j) ? nums1[i++] : nums2[j++];
        }
        return res;
    }
    private boolean greater(int[] nums1, int i, int[] nums2, int j) {
        int n1 = nums1.length, n2 = nums2.length;
        while (i < n1 && j < n2 && nums1[i] == nums2[j]) {
            i++;
            j++;
        }
        return j == n2 || (i < n1 && nums1[i] > nums2[j]);
    }
}
