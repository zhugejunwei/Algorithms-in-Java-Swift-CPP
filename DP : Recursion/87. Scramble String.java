// ============================ Recursion ============================= //
public class Solution {
    public boolean isScramble(String s1, String s2) {
        int n = s1.length();
        if (n == 0) return true;
        if (s1.equals(s2)) return true;
        int[] ch = new int[128];
        for (int i = 0; i < n; i++) {
            ch[s1.charAt(i)]++;
            ch[s2.charAt(i)]--;
        }
        for (int i = 0; i < 128; i++) {
            if (ch[i] != 0) return false;
        }
        for (int i = 1; i < n; i++) {
            if (isScramble(s1.substring(0,i), s2.substring(0,i)) && isScramble(s1.substring(i), s2.substring(i)))
                return true;
            if (isScramble(s1.substring(0,i), s2.substring(n-i)) && isScramble(s1.substring(i), s2.substring(0, n - i)))
                return true;
        }
        return false;
    }
}

// ============================ DP ========================== //
public class Solution {
    public boolean isScramble(String s1, String s2) {
        int len = s1.length();
        if (len == 0 || s1.equals(s2)) return true;
        boolean[][][] match = new boolean[len+1][len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                match[1][i][j] = (s1.charAt(i) == s2.charAt(j));
            }
        }
        for (int size = 2; size <= len; size++) {
            for (int i = 0; i <= len - size; i++) {
                for (int j = 0; j <= len - size; j++) {
                    for (int k = 1; k < size && !match[size][i][j]; k++) {
                        match[size][i][j] = (match[k][i][j] && match[size-k][i+k][j+k])
                        || (match[k][size-k+i][j] && match[size-k][i][j+k]);
                    }
                }
            }
        }
        return match[len][0][0];
    }
}
