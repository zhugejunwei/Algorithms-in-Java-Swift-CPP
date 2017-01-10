public class Solution {
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n <= 1) return n;
        int idx = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] != nums[i - 1]) nums[idx++] = nums[i];
        }
        return idx;
    }
}
