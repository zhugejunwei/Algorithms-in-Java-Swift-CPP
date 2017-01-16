// maximum sum in any contiguous subarray.

public class Main
{
    public static int maxSublist(int n, int[] nums) {
        int max = nums[0];
        int sum = nums[0];
        int sum_min = Math.min(0,nums[0]);
        for (int i = 1; i < n; i++) {
            sum += nums[i];
            if (sum - sum_min > max) {
                max = sum - sum_min;
            }
            if (sum < sum_min) {
                sum_min = sum;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {2,-1};
        int res = maxSublist(2, nums);
        System.out.println(res);
    }
}