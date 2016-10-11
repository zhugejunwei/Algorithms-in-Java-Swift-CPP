package io.kongming;

// Time elapsed: 1962ms

public class Main {
    private static final int n = 15;
    private static int count = 0;
    private static int shu, pie, na;
    
    public static void main(String[] args) {
        long tic = System.currentTimeMillis();
        DFS(0);
        long toc = System.currentTimeMillis();
        System.out.println("Total solutions: " + count);
        System.out.println("Time elapsed: " + (toc - tic) + "ms");
    }
    
    private static void DFS(int row) {
        int ok = ((1 << n) - 1) & ~(shu | (pie >> row) | (na >> (n - 1 - row)));
        while (ok != 0) {
            int p = ok & -ok;
            ok ^= p;
            if (row == n - 1) {
                count++;
            } else {
                shu ^= p; pie ^= (p << row); na ^= (p << (n - 1 - row));
                DFS(row + 1);
                shu ^= p; pie ^= (p << row); na ^= (p << (n - 1 - row));
            }
        }
    }
}
