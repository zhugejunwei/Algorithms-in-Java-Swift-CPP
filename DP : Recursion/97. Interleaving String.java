public class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()) return false;
        int len1 = s1.length();
        int len2 = s2.length();
        boolean[][] table = new boolean[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                if (i == 0 && j == 0) table[i][j] = true;  // s1,s2,s3,all length = 0
                else if (i == 0) {
                    table[i][j] = table[i][j-1] && (s2.charAt(j-1) == s3.charAt(i+j-1));
                } else if (j == 0) {
                    table[i][j] = table[i-1][j] && (s1.charAt(i-1) == s3.charAt(i+j-1));
                } else {
                    table[i][j] = (table[i-1][j] && s1.charAt(i-1) == s3.charAt(i+j-1))
                    ||(table[i][j-1] && s2.charAt(j-1) == s3.charAt(i+j-1));
                }
            }
        }
        return table[len1][len2];
    }
}
