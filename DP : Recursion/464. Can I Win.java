public class Solution {
    int vis = 0;
    Map<Integer, Boolean> map = new HashMap();
    public boolean canIWin(int m, int n) {
        int sum = (1 + m)*m/2;
        if (sum < n || m + 1 == n) return false;
        if (m >= n) return true; // 这里把第一个人选到0排除了
        
        return canWin(m, n);
    }
    
    private boolean canWin(int m, int n) {
        // 所以这里到0是第二个人。如果第二个人到了0，那么前一个人就是false；
        // 这里的return fasle是指的现在的人不能选了，所以之前选i的人是win的。
//        if (n <= 0) return false;
        if (map.containsKey(vis)) return map.get(vis);
        // 把n的判断条件放到这里面也许更好理解，也可以更好的和flip game2一起理解
        for (int i = 1; i <= m && n > 0; i++) {
            if ((vis >>> i & 1) == 1) continue;
            vis |= (1 << i);
            boolean win = !canWin(m, n - i); // 第二个人不能赢，则第一个人能赢
            vis &= ~(1 << i);
            if (win) {
                map.put(vis, true);
                return true;
            }
        }
        map.put(vis, false);
        return false;
    }
}

// simpler version
public class Solution {
    public boolean canIWin(int m, int d) {
        if (d == 0) return true;
        if ((1 + m)*m/2 < d) return false;
        return canWin(m, d, 0, new HashMap());
    }
    
    private boolean canWin(int m, int d, int mask, Map<Integer, Boolean> map) {
        if (map.containsKey(mask)) return map.get(mask);
        for (int i = 1; i <= m && d > 0; i++) {
            if ((mask >> i & 1) == 1) continue;
            if (!canWin(m, d - i, mask ^ (1 << i), map)) {
                map.put(mask, true);
                return true;
            }
        }
        map.put(mask, false);
        return false;
    }
}
