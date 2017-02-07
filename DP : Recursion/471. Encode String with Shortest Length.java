// https://discuss.leetcode.com/topic/71963/accepted-solution-in-java/11

// basic DP solution
public class Solution {
    public String encode(String s) {
        if (s == null || s.length() <= 4) return s;
        int n = s.length();
        String[][] dp = new String[n][n];
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                int j = i + len - 1;
                String sub = s.substring(i, j + 1);
                dp[i][j] = sub;
                // if len <= 4, we don't need to encode it
                if (len < 4) continue;
                // if the length of combination of two sub_substrings is less than original substring
                for (int k = i; k < j; k++) {
                    if (dp[i][k].length() + dp[k+1][j].length() < dp[i][j].length()) {
                        dp[i][j] = dp[i][k] + dp[k+1][j];
                    }
                }
                // check whether it self could be encoded
                for (int k = 0; k < sub.length(); k++) {
                    String ss = sub.substring(0, k + 1);
                    if (ss != null && sub.length() % ss.length() == 0 && sub.replaceAll(ss, "").length() == 0) {
                        // we should use dp[i][i + k] here, since dp[i][i+k] could also be a encoded string
                        String encoded = sub.length()/ss.length() + "[" + dp[i][i + k] + "]";
                        if (encoded.length() < dp[i][j].length()) {
                            dp[i][j] = encoded;
                        }
                    }
                }
            }
        }
        
        return dp[0][n - 1];
    }
}

// modified DP solution, using KMP
public class Solution {
    public String encode(String s) {
        if (s == null || s.length() <= 4) return s;
        int n = s.length();
        String[][] dp = new String[n][n];
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                int j = i + len - 1;
                String sub = s.substring(i, j + 1);
                dp[i][j] = sub;
                if (len < 4) continue;
                
                for (int k = i; k < j; k++) {
                    // dp[i][k].length() + dp[k+1][j].length()
                    // is much faster than (dp[i][k] + dp[k+1][j]).length()
                    // because s1 + s2 is O(n) time, s1.length() + s2.length() is O(1) time
                    if (dp[i][k].length() + dp[k+1][j].length() < dp[i][j].length()) {
                        dp[i][j] = dp[i][k] + dp[k+1][j];
                    }
                }
                
                String pat = KMP(sub);
                if (pat.length() < sub.length()) {
                    String encoded = sub.length()/pat.length() + "[" + dp[i][i + pat.length() - 1] + "]";
                    if (encoded.length() < dp[i][j].length())
                        dp[i][j] = encoded;
                }
            }
        }
        return dp[0][n - 1];
    }
    
    // using KMP to find pattern
    private String KMP(String s) {
        int len = s.length();
        int[] lps = new int[len];
        int i = 1, j = 0;
        while (i < len) {
            if (s.charAt(i) == s.charAt(j)){
                lps[i++] = ++j;
            } else if (j == 0) {
                lps[i++] = 0;
            } else {
                j = lps[j - 1];
            }
        }
        
        int l = len - lps[len - 1];
        if (l < len && len % l == 0) {
            return s.substring(0, l);
        }
        return s;
    }
}
