public class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int l = matrix[0][0], r = matrix[n - 1][n - 1];
        while (l < r) {
            int m = l + (r - l)/2;
            int count = binaryCount(matrix, m, n);
            if (count >= k) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }
    
    private int binaryCount(int[][] matrix, int m, int n) {
        int res = 0, i = n - 1, j = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] > m) i--;
            else {
                res += i + 1;
                j++;
            }
        }
        return res;
    }
}
