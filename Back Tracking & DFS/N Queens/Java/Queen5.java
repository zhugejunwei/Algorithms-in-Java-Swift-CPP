package io.kongming;

// Time elapsed: 1350ms

public class Main {
    private static final int n = 15;
    private static int count = 0;
    
    public static void main(String[] args) {
        long tic = System.currentTimeMillis();
        DFS(0, 0, 0, 0);
        long toc = System.currentTimeMillis();
        System.out.println("Total solutions: " + count);
        System.out.println("Time elapsed: " + (toc - tic) + "ms");
    }
    
    private static void DFS(int row, int shu, int pie, int na) {
        int ok = ((1 << n) - 1) & ~(shu | pie | na);
        while (ok != 0) {
            int p = ok & -ok;
            ok ^= p;
            if (row == n - 1) {
                count++;
            } else {
                DFS(row + 1, shu ^ p, (pie ^ p) << 1, (na ^ p) >> 1);
            }
        }
    }
}
