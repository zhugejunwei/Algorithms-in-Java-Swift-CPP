public class Solution {
    public int findMaximumXOR(int[] nums) {
        int mask = 0, max = 0;
        for (int i = 31; i >= 0; i--) {
            mask |= (1<<i);
            Set<Integer> set = new HashSet();
            for (int n : nums) {
                set.add(n & mask);
            }
            int tmp = (max | (1<<i));
            for (int prefix : set) {
                if (set.contains(prefix ^ tmp)) {
                    max = tmp;
                    break;
                }
            }
        }
        return max;
    }
}
