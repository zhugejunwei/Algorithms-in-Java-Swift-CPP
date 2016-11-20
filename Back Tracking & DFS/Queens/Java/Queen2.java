package io.kongming;

// Time elapsed: 16024ms

public class Main {
    private static final int n = 15;
    private static int count = 0;
    private static boolean[] shu, pie, na;
    
    public static void main(String[] args) {
        shu = new boolean[n];
        pie = new boolean[2 * n - 1];
        na = new boolean[2 * n - 1];
        long tic = System.currentTimeMillis();
        DFS(0);
        long toc = System.currentTimeMillis();
        System.out.println("Total solutions: " + count);
        System.out.println("Time elapsed: " + (toc - tic) + "ms");
    }
    
    private static void DFS(int row) {
        for (int col = 0; col < n; col++) {
            int j = col + row, k = n - 1 + col - row;
            if (shu[col] || pie[j] || na[k]) continue;
            if (row == n - 1) {
                count++;
            } else {
                shu[col] = true; pie[j] = true; na[k] = true;
                DFS(row + 1);
                shu[col] = false; pie[j] = false; na[k] = false;
            }
        }
    }
}
