public class Solution {
    Map<Integer, Boolean> map = new HashMap();
    int mask;
    public boolean canIWin(int m, int n) {
        int sum = (1 + m)  * m / 2;
        if (sum < n || (1 + m) == n) return false;
        if (m >= n) return true;
        return helper(m, n);
    }
    
    private boolean helper(int m, int target) {
        if (target <= 0) return false;
        if (!map.containsKey(mask)) {
            for (int i = 1; i <= m; i++) {
                if (((mask >>> (i - 1)) & 1) == 1) continue;
                mask |= (1 << (i-1));
                if (!helper(m, target - i)) {
                    mask &= ~(1 << (i-1));
                    map.put(mask, true);
                    
                    return true;
                }
                mask &= ~(1 << (i-1));
            }
            map.put(mask, false);
        }
        return map.get(mask);
    }
}
