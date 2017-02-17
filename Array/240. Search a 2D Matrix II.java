// divide and conquer
public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;
        int m = matrix.length, n = matrix[0].length;
        return helper(matrix, target, m, n, 0, m-1, 0, n-1);
    }
    
    private boolean helper(int[][] matrix, int target, int m, int n, int rs, int re, int cs, int ce) {
        if (rs < 0 || re >= m || cs < 0 || ce >= n || rs > re || cs > ce) return false;
        int rm = rs + (re - rs)/2, cm = cs + (ce - cs)/2;
        
        if (matrix[rm][cm] == target) return true;
        else if (target > matrix[rm][cm]) {
            return helper(matrix, target, m, n, rs, rm, cm + 1, ce)
            || helper(matrix, target, m, n, rm + 1, re, cs, cm)
            || helper(matrix, target, m, n, rm + 1, re, cm + 1, ce);
        } else {
            return helper(matrix, target, m, n, rs, rm - 1, cs, cm - 1)
            || helper(matrix, target, m, n, rs, rm - 1, cm, ce)
            || helper(matrix, target, m, n, rm, re, cs, cm - 1);
        }
    }
}

// below is faster
public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length < 1 || matrix[0].length <1) {
            return false;
        }
        int col = matrix[0].length-1;
        int row = 0;
        while(col >= 0 && row <= matrix.length-1) {
            if(target == matrix[row][col]) {
                return true;
            } else if(target < matrix[row][col]) {
                col--;
            } else if(target > matrix[row][col]) {
                row++;
            }
        }
        return false;
    }
}
