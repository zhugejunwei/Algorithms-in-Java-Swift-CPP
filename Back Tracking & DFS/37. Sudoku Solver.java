// time complexity O(9^m), where m is the number of blank cells
public class Solution {
    public void solveSudoku(char[][] board) {
        helper(board);
    }
    
    private boolean helper(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') continue;
                for (char c = '1'; c <= '9'; c++) {
                    if (isValid(board, i, j, c)) {
                        board[i][j] = c;
                        if (helper(board)) {
                            return true;
                        } else {
                            board[i][j] = '.';
                        }
                    }
                }
                return false;
            }
        }
        return true;
    }
    
    private boolean isValid(char[][] board, int i, int j, char c) {
        for (int k = 0; k < 9; k++) {
            if (board[i][k] == c) return false;
            if (board[k][j] == c) return false;
            int ri = i/3*3 + k/3, rj = j/3*3 + k%3;
            if (board[ri][rj] == c) return false;
        }
        return true;
    }
}
