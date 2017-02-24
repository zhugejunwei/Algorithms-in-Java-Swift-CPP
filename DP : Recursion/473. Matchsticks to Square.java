// DP
public class Solution {
    public boolean makesquare(int[] nums) {
        int sum = 0, n = nums.length;
        for (int num : nums) sum += num;
        if (sum == 0 || n == 0 || (sum & 0b11) != 0) return false;
        sum >>>= 2;
        boolean[] dp = new boolean[1 << n];
        List<Integer> parts = new ArrayList();
        int all = (1 << n) - 1; // all possible choices
        // try all choices, 1 mask is 1 choice
        // time complexity O(2^n)
        for (int mask = 0; mask <= all; mask++) {
            int tmp = 0;
            for (int i = 0; i < n; i++) { // retrieve "1", choose num at i
                if (((mask >>> i) & 1) == 1)
                    tmp += nums[i];
            }
            if (tmp == sum) { // check whether it is valid
                for (int x : parts) {
                    // check whether the new choice is conflit with others
                    if ((mask & x) == 0) {
                        // if there is an exist mask x has no conflit with current mask
                        // mark their combination "mask1 + mask2" as true
                        dp[mask | x] = true;
                        // check whether there is another "mask3 + mask4" is valid
                        // if there is, return true
                        if (dp[all ^ (mask | x)]) {
                            return true;
                        }
                    }
                }
                // record every kinds of equal-to-sum combination
                parts.add(mask);
            }
        }
        return false;
    }
}

// backtracking + memorization
public class Solution {
    Map<Integer, Boolean> map = new HashMap();
    public boolean makesquare(int[] nums) {
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % 4 != 0 || sum == 0) return false;
        sum >>>= 2;
        Arrays.sort(nums);
        return canMatch(nums, sum, 0, 0, 0);
    }
    
    private boolean canMatch(int[] nums, int target, int sum, int vis, int count) {
        if (count == 4) return true;
        if (map.containsKey(vis)) return map.get(vis);
        for (int i = 0; i < nums.length; i++) {
            if ((vis >>> i & 1) == 1 || sum + nums[i] > target) continue;
            if (sum + nums[i] == target)
                if (canMatch(nums, target, 0, vis ^ (1 << i), count + 1)) {
                    map.put(vis, true);
                    return true;
                }
            if (canMatch(nums, target, sum + nums[i], vis ^ (1 << i), count)) {
                map.put(vis, true);
                return true;
            }
        }
        map.put(vis, false);
        return false;
    }
}
