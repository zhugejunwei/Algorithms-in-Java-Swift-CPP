public class Solution {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        // a1, a2
        int a1 = (C - A) * (D - B);
        int a2 = (G - E) * (H - F);
        
        // overlap
        int left = Math.max(A, E);
        int right = Math.min(C, G);
        int bottom = Math.max(B, F);
        int top = Math.min(D, H);
        int overlap = 0;
        if (left < right && bottom < top) {
            overlap = (right - left) * (top - bottom);
        }
        return a1 + a2 - overlap;
    }
}
