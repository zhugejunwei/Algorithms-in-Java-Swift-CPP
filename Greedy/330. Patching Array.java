public class Solution {
    public int minPatches(int[] nums, int n) {
        long sum = 0;
        int add = 0, i = 0;
        while (sum < n) {
            if (i < nums.length && nums[i] - 1 <= sum) {
                sum += nums[i++];
            } else {
                // add sum + 1 to the end of array, [1,2,3] sum = 6, add 7 to [1,2,3,7]
                sum += sum + 1;
                add++;
            }
        }
        return add;
    }
}
