public class Solution {
    public int[][] multiply(int[][] A, int[][] B) {
        // ra, cb
        int ra = A.length, ca = A[0].length, cb = B[0].length;
        int[][] res = new int[ra][cb];
        for (int i = 0; i < ra; i++) {
            for (int j = 0; j < ca; j++) {
                if (A[i][j] == 0) continue;
                for (int k = 0; k < cb; k++) {
                    if (B[j][k] == 0) continue;
                    res[i][k] += A[i][j] * B[j][k];
                }
            }
        }
        return res;
    }
}
