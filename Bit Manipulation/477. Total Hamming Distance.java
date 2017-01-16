public class Solution {
    public int totalHammingDistance(int[] nums) {
        int res = 0;
        for (int i = 0; i < 31; i++) {
            int zero = 0, one = 0;
            for (int n : nums) {
                if ((n >>> i & 1) == 0) { // count zeros
                    zero++;
                } else { // count ones
                    one++;
                }
            }
            // 所有数同位上的0的数量 ＊ 1的数量 ＝ 该位上的total hamming dist
            res += zero * one;
        }
        return res;
    }
}
