public class Solution {
    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            HashSet<Character> row = new HashSet<Character>();
            HashSet<Character> col = new HashSet<Character>();
            HashSet<Character> cub = new HashSet<Character>();
            
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.' && !row.add(board[i][j])) {
                    return false;
                }
                if (board[j][i] != '.' && !col.add(board[j][i])) {
                    return false;
                }
                int rowIndex = 3 * (i/3);
                int colIndex = 3 * (i%3);
                if (board[rowIndex + j/3][colIndex + j%3] != '.' && !cub.add(board[rowIndex + j/3][colIndex + j%3])) {
                    return false;
                }
            }
        }
        return true;
    }
}

// more concise solution

public class Solution {
    public boolean isValidSudoku(char[][] board) {
        boolean[][] row = new boolean[9][9], col = new boolean[9][9], box = new boolean[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') continue;
                int num = board[i][j] - '0' - 1;
                int k = i/3*3 + j/3;
                if (row[i][num] || col[j][num] || box[k][num]) return false;
                row[i][num] = col[j][num] = box[k][num] = true;
            }
        }
        return true;
    }
}
