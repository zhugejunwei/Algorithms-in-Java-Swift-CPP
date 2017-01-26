public class Solution {
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0) return word.length() == 0;
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != word.charAt(0)) continue;
                board[i][j] ^= 128;
                if (exist(board, i, j, word.toCharArray(), 1, m, n)) return true;
                board[i][j] ^= 128;
            }
        }
        return false;
    }
    
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private boolean exist(char[][] board, int i, int j, char[] word, int idx, int m, int n) {
        if (idx == word.length) return true;
        
        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x >= m || y >= n || x < 0 || y < 0 || board[x][y] != word[idx]) continue;
            board[x][y] ^= 128;
            if (exist(board, x, y, word, idx + 1, m, n)) return true;
            board[x][y] ^= 128;
        }
        return false;
    }
}
