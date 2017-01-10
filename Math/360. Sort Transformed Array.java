public class Solution {
    int a, b, c;
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        this.a = a; this.b = b; this.c = c;
        int n = nums.length, i = 0, j = n - 1;
        int idx = a >= 0 ? n - 1 : 0;
        int[] res = new int[n];
        while (i <= j) {
            if (a >= 0) {
                res[idx--] = cal(nums[i]) > cal(nums[j]) ? cal(nums[i++]) : cal(nums[j--]);
            } else {
                res[idx++] = cal(nums[i]) < cal(nums[j]) ? cal(nums[i++]) : cal(nums[j--]);
            }
        }
        return res;
    }
    
    private int cal(int x) {
        return a * x * x + b * x + c;
    }
}
