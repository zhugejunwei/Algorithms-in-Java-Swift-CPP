public class Solution {
    // minHP + hp >= 1
    // minHP = Math.max(1 - hp, 1);
    public int calculateMinimumHP(int[][] hp) {
        int m = hp.length, n = hp[0].length;
        for (int i = m-1; i >= 0; i--) {
            for (int j = n-1; j >= 0; j--) {
                if (i == m-1 && j == n-1) {
                    hp[i][j] = Math.max(1, 1 - hp[i][j]);
                } else if (i == m-1) {
                    hp[i][j] = Math.max(1, hp[i][j+1] - hp[i][j]);
                } else if (j == n-1) {
                    hp[i][j] = Math.max(1, hp[i+1][j] - hp[i][j]);
                } else {
                    hp[i][j] = Math.max(1, Math.min(hp[i+1][j], hp[i][j+1]) - hp[i][j]);
                }
            }
        }
        return hp[0][0];
    }
}
