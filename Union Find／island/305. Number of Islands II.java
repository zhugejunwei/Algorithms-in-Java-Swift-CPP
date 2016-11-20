public class Solution {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        int[][] directions = {{1,0},{0,1},{-1,0},{0,-1}};
        int[][] matrix = new int[m][n];
        int[] roots = new int[m*n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                roots[i*n + j] = i*n+j;
            }
        }
        int count = 0;
        List<Integer> list = new ArrayList();
        for (int[] pos : positions) {
            int i = pos[0], j = pos[1];
            count++;
            matrix[i][j] = 1;
            for (int[] dir : directions) {
                int ii = i+dir[0], jj = j+dir[1];
                if (ii >= 0 && ii < m && jj >= 0 && jj < n && matrix[ii][jj] == 1) {
                    int root0 = find(roots, i*n+j);
                    int root1 = find(roots, ii * n + jj);
                    if (root0 != root1) {
                        roots[root0] = root1;
                        count--;
                    }
                }
            }
            list.add(count);
        }
        return list;
    }
    
    private int find(int[] roots, int id) {
        while (roots[id] != id) {
            roots[id] = roots[roots[id]];
            id = roots[id];
        }
        return id;
    }
}
