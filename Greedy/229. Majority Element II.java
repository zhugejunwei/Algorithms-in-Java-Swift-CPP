public class Solution {
    public List<Integer> majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList();
        int[] c = new int[2], num = new int[2];
        for (int n : nums) {
            if (num[0] == n) c[0]++;
            else if (num[1] == n) c[1]++;
            else if (c[0] == 0) {
                num[0] = n;
                c[0]++;
            }
            else if (c[1] == 0) {
                num[1] = n;
                c[1]++;
            }
            else {
                c[0]--;
                c[1]--;
            }
        }
        List<Integer> res = new ArrayList();
        for (int i = 0; i <= 1; i++) {
            if (c[i] > 0) {
                int t = 0;
                for (int n : nums) {
                    if (n == num[i])
                        t++;
                }
                if (t > nums.length/3) res.add(num[i]);
            }
        }
        return res;
    }
}
