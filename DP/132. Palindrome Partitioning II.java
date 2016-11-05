/*
 // From middle to left and right,
 // 从中间向两边扩散，寻找pl，if(pl) -> cut[i+j] = cut[i-j] + 1 || = cut[i-j+1] + 1
 
 // 1. cut[i] is the minimum of cut[j - 1] + 1 (j <= i), if [j, i] is palindrome.
 
 // 2. If [j, i] is palindrome, [j + 1, i - 1] is palindrome, and c[j] == c[i].
*/
public class Solution {
    public int minCut(String s) {
        char[] c = s.toCharArray();
        int n = c.length;
        int[] cut = new int[n];
        boolean[][] pl = new boolean[n][n];
        
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i; j >= 0; j--) { // this is faster
                if (c[j] == c[i] && (j + 1 > i - 1 || pl[j + 1][i - 1])) { // (j + 1 > i - 1 || pl[j + 1][i - 1])
                    pl[j][i] = true;
                    min = j == 0 ? 0 : Math.min(min, cut[j - 1] + 1);
                }
            }
            cut[i] = min;
        }
        return cut[n - 1];
    }
}

// ================= more concise solution ============= //

public class Solution {
    public int minCut(String s) {
        char[] c = s.toCharArray();
        int n = c.length;
        int[] cut = new int[n + 1];
        for (int i = 0; i <= n; i++) cut[i] = i - 1;
        
        for (int i = 0; i < n; i++) {
            // odd length palindrome
            for (int j = 0; i - j >= 0 && i + j < n && c[i-j]==c[i+j]; j++) {
                cut[i+j+1] = Math.min(cut[i+j+1], 1 + cut[i-j]);
            }
            
            // even length palindrome
            for (int j = 1; i - j + 1 >= 0 && i + j < n && c[i - j + 1] == c[i + j]; j++) {
                cut[i+j+1] = Math.min(cut[i+j+1], 1 + cut[i-j+1]);
            }
        }
        return cut[n];
    }
}
