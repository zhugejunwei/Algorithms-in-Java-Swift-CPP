public class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        if (n > 10) return countNumbersWithUniqueDigits(10);
        int count = 1; // n == 0
        long max = (long)Math.pow(10,n);
        boolean[] vis = new boolean[10];
        
        for (int i = 1; i < 10; i++) {
            vis[i] = true;
            count += dfs(i, max, vis);
            vis[i] = false;
        }
        return count;
    }
    private int dfs(long pre, long max, boolean[] vis) {
        int count = 0;
        if (pre < max) {
            count++;
        } else return count;
        
        for (int i = 0; i < 10; i++) {
            if (vis[i]) continue;
            vis[i] = true;
            long cur = 10 * pre + i;
            count += dfs(cur, max, vis);
            vis[i] = false;
        }
        return count;
    }
}
