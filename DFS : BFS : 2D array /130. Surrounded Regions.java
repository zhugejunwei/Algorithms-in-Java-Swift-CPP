public class Solution {
    int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
    int m, n;
    public void solve(char[][] board) {
        if (board.length < 2 || board[0].length < 2 ) return;
        m = board.length;
        n = board[0].length;
        
        // top row, bot row
        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O') bound(board, 0, j);
            if (board[m-1][j] == 'O') bound(board, m-1, j);
        }
        // left col, right col
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') bound(board, i, 0);
            if (board[i][n-1] == 'O') bound(board, i, n-1);
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') board[i][j] = 'X';
                else if (board[i][j] == '1') board[i][j] = 'O';
            }
        }
    }
    
    private void bound(char[][] board, int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n) return;
        if (board[i][j] == 'O') board[i][j] = '1';
        if (i > 1 && board[i-1][j] == 'O') bound(board, i - 1, j);
        if (i < m - 2 && board[i + 1][j] == 'O') bound(board, i + 1, j);
        if (j > 1 && board[i][j - 1] == 'O') bound(board, i, j - 1);
        if (j < n - 2 && board[i][j + 1] == 'O') bound(board, i, j + 1);
    }
}
