public class Solution {
    public void gameOfLife(int[][] board) {
        // 00 die -> die
        // 01 live -> die
        // 10 die -> live
        // 11 live -> live
        if (board == null || board.length == 0) return;
        int m = board.length, n = board[0].length;
        // find the live ones
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if ((board[i][j] & 1) == 1 && (countNeighbor(board, i, j) == 2 || countNeighbor(board, i, j) == 3)) {
                    board[i][j] |= (1 << 1);
                    System.out.println(board[i][j]);
                } else if ((board[i][j] & 1) == 0 && countNeighbor(board, i, j) == 3) {
                    board[i][j] |= (1 << 1);
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] >>>= 1;
            }
        }
    }
    
    private int countNeighbor(int[][] board, int i, int j) {
        int count = 0, m = board.length, n = board[0].length;
        for (int x = Math.max(i - 1, 0); x <= Math.min(i + 1, m - 1); x++) {
            for (int y = Math.max(j - 1, 0); y <= Math.min(j + 1, n - 1); y++) {
                count += (board[x][y] & 1);
            }
        }
        count -= (board[i][j] & 1);
        return count;
    }
}
