public class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return new ArrayList();
        int m = matrix.length, n = matrix[0].length;
        int left = 0, right = n-1, top = 0, bot = m-1;
        List<Integer> res = new ArrayList();
        int idx = 0, i = 0, j = 0;
        while (idx++ < m*n) {
            for (j = left, i = top; i <= bot && j <= right; j++) {
                res.add(matrix[i][j]);
            }
            top++;
            for (i = top, j = right; j >= left && i <= bot; i++) {
                res.add(matrix[i][j]);
            }
            right--;
            for (j = right, i = bot; i >= top && j >= left; j--) {
                res.add(matrix[i][j]);
            }
            bot--;
            for (i = bot, j = left; j <= right && i >= top; i--) {
                res.add(matrix[i][j]);
            }
            left++;
        }
        return res;
    }
}
