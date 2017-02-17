public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int m = matrix.length, n = matrix[0].length;
        int l = 0, r = m - 1, t = 0;
        while (l <= r) {
            int mid = l + (r - l)/2;
            if (target > matrix[mid][n-1]) {
                l = mid + 1;
            } else {
                if (target > matrix[mid][0]) {
                    return find(matrix, mid, n, target);
                } else if (target < matrix[mid][0]) {
                    r = mid - 1;
                } else {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean find(int[][] matrix, int row, int n, int target) {
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = l + (r-l)/2;
            if (target > matrix[row][mid]) {
                l = mid + 1;
            } else if (target < matrix[row][mid]) {
                r = mid - 1;
            } else return true;
        }
        return false;
    }
}
