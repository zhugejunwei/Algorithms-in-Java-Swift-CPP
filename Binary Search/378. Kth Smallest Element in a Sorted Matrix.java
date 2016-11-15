public class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int l = matrix[0][0], h = matrix[n-1][n-1];
        while (l < h) {
            int m = l + (h - l)/2;
            int count = binarySearch(matrix, m);
            if (count < k) {
                l = m + 1;
            } else {
                h = m;
            }
        }
        return l;
    }
    public int binarySearch(int[][] matrix, int target) {
        int n = matrix.length, i = n-1, j = 0;
        int res = 0;
        while (i >= 0 && j <= n-1) {
            if (matrix[i][j] > target) i--;
            else {
                j++;
                res += i + 1;
            }
        }
        return res;
    }
}
