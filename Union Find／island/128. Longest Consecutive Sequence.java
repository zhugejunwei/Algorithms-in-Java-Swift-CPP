public class Solution {
    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> map = new HashMap();
        int res = 0;
        for (int n : nums) {
            if (!map.containsKey(n)) {
                int left = map.containsKey(n - 1) ? map.get(n - 1) : 0;
                int right = map.containsKey(n + 1) ? map.get(n + 1) : 0;
                int sum = left + right + 1;
                map.put(n, sum);
                res = Math.max(sum, res);
                // extend to its left and right boundaries
                map.put(n - left, sum);
                map.put(n + right, sum);
            }
        }
        return res;
    }
}
