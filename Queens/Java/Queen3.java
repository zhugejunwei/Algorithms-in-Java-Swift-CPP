package io.kongming;

// Time elapsed: 10302ms

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
        for (int col = 0; col < n; col++) {
            if ((((shu >> col) | (pie >> (col + row)) | (na >> (n - 1 + col - row))) & 1) != 0) continue;
            if (row == n - 1) {
                count++;
            } else {
                shu ^= (1 << col); pie ^= (1 << (row + col)); na ^= (1 << (n - 1 + col - row));
                DFS(row + 1);
                shu ^= (1 << col); pie ^= (1 << (row + col)); na ^= (1 << (n - 1 + col - row));
            }
        }
    }
}
