public class Solution {
    public int majorityElement(int[] nums) {
        int count = 0, res =  0;
        for (int n : nums) {
            if (count == 0) res = n;
            if (res != n) count--;
            if (res == n) count++;
        }
        return res;
    }
}
