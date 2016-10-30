// DFS 5ms
public class Solution {
    int m, n;
    boolean[][] flag;
    int[][] dir = {{1,0}, {0,1}, {-1,0}, {0,-1}};
    
    public int countBattleships(char[][] board) {
        if (board.length == 0 || board == null || board[0].length == 0) return 0;
        m = board.length;
        n = board[0].length;
        flag = new boolean[m][n];
        int result = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'X' && !flag[i][j]) {
                    result++;
                    DFS(board, i, j);
                }
            }
        }
        return result;
    }
    
    private void DFS(char[][] board, int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] == '.' || flag[i][j]) return;
        flag[i][j] = true;
        for (int d = 0; d < 4; d++) DFS(board, i + dir[d][0], j + dir[d][1]);
    }
}

// BFS
public class Solution {
    // BFS
    int[][] dir = {{1,0}, {0,1}, {-1,0}, {0,-1}};
    public int countBattleships(char[][] board) {
        if (board.length == 0 || board == null) return 0;
        
        int m = board.length, n = board[0].length;
        boolean[][] flag = new boolean[m][n];
        int result = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'X' && !flag[i][j]) {
                    result++;
                    Queue<tuple> q = new ArrayDeque();
                    q.add(new tuple(i, j));
                    while (!q.isEmpty()) {
                        tuple t = q.poll();
                        flag[t.x][t.y] = true;
                        for (int d = 0; d < 4; d++) {
                            int ni = t.x + dir[d][0], nj = t.y + dir[d][1];
                            if (ni < 0 || ni >= m || nj < 0 || nj >= n || board[ni][nj] == '.' || flag[ni][nj]) continue;
                            q.add(new tuple(ni, nj));
                        }
                    }
                }
            }
        }
        return result;
    }
}

class tuple {
    int x;
    int y;
    tuple(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
