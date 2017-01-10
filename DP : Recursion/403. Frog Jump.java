public class Solution {
    public boolean canCross(int[] stones) {
        int len = stones.length;
        Map<Integer, Set<Integer>> map = new HashMap(); // stones, steps
        for (int i = 0; i < len; i++) {
            map.put(stones[i], new HashSet());
        }
        map.get(0).add(1);
        
        for (int stone : stones) {
            if (map.get(stone).isEmpty()) continue;
            for (int step : map.get(stone)) {
                int reach = step + stone;
                if (reach == stones[len - 1])
                    return true;
                if (map.get(reach) == null) continue;
                map.get(reach).add(step);
                if (step - 1 > 0) map.get(reach).add(step - 1);
                map.get(reach).add(step + 1);
            }
        }
        return false;
    }
}

// recursion + memo

public class Solution {
    public boolean canCross(int[] s) {
        int n = s.length;
        int[][] map = new int[n][n]; // pos, step
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = -1;
            }
        }
        return canCross(s, 0, 0, map, n); // pos = 0, step = 0
    }
    
    private boolean canCross(int[] s, int pos, int step, int[][] map, int n) {
        if (map[pos][step] != -1) return map[pos][step] == 1;
        if (pos == n - 1) {
            map[pos][step] = 1;
            return true;
        }
        // k is the next pos
        for (int k = pos + 1; k < n; k++) {
            // if the kth stone is too close
            if (s[k] < s[pos] + step - 1) continue;
            // if the kth stone is too far to reach,
            // and because it is not too close,
            // it is just cannot be reached, just return false;
            else if (s[k] > s[pos] + step + 1) {
                map[pos][step] = 0;
                return false;
            } else if (canCross(s, k, s[k] - s[pos], map, n)) {
                map[pos][step] = 1;
                return true;
            }
        }
        map[pos][step] = 0;
        return false;
    }
}
