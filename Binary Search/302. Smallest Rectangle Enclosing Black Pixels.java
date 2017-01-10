// binary search
public class Solution {
    public int minArea(char[][] image, int x, int y) {
        int m = image.length, n = image[0].length;
        int rowMin = binarySearch(image, 0, x, 0, n, false, true); //lower, upper, min, max, horizontal, golower
        int rowMax = binarySearch(image, x + 1, m, 0, n, false, false);
        int colMin = binarySearch(image, 0, y, rowMin, rowMax, true, true);
        int colMax = binarySearch(image, y + 1, n, rowMin, rowMax, true, false);
        return (colMax - colMin) * (rowMax - rowMin);
    }
    
    private int binarySearch(char[][] image, int lower, int upper, int min, int max, boolean horizontal, boolean golower) {
        while (lower < upper) {
            int mid = lower + (upper - lower)/2;
            boolean inside = false;
            for (int i = min; i < max; i++) {
                if ((horizontal ? image[i][mid] : image[mid][i]) == '1') {
                    inside = true;
                    break;
                }
            }
            if (inside == golower) {
                upper = mid;
            } else {
                lower = mid + 1;
            }
        }
        return lower;
    }
}

// dfs
public class Solution {
    int[][] dirs = {{0, 1},{0, -1},{1, 0},{-1, 0}};
    int left, right, up, down;
    public int minArea(char[][] image, int x, int y) {
        if (image == null || image.length == 0) return 0;
        int m = image.length, n = image[0].length;
        boolean[][] vis = new boolean[m][n];
        vis[x][y] = true;
        left = y; right = y; down = x; up = x;
        findEdge(image, x, y, vis, m, n);
        return (right - left + 1) * (down - up + 1);
    }
    
    private void findEdge(char[][] image, int x, int y, boolean[][] vis, int m, int n) {
        if (y < left) left = y;
        if (y > right) right = y;
        if (x < up) up = x;
        if (x > down) down = x;
        
        for (int[] d : dirs) {
            int x1 = x + d[0], y1 = y + d[1];
            if (x1 < 0 || y1 < 0 || x1 >= m || y1 >= n || vis[x1][y1] || image[x1][y1] == '0') continue;
            vis[x1][y1] = true;
            findEdge(image, x1, y1, vis, m, n);
        }
    }
}
