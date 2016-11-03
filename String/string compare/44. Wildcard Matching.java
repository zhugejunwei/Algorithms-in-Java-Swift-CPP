// ============================= Iteration fastest ============================//

public class Solution {
    public boolean isMatch(String str, String pattern) {
        int s = 0, p = 0, star = -1, match = 0;
        int len1 = str.length(), len2 = pattern.length();
        while (s < len1) {
            if (p < len2 && (pattern.charAt(p) == str.charAt(s) || pattern.charAt(p) == '?')) {
                // p[j] == s[i] || p[j] == '?'
                s++;
                p++;
            } else if (p < len2 && pattern.charAt(p) == '*') {
                // if p[j] == '*'
                star = p;
                match = s;
                p++;
            } else if (star != -1) {
                p = star + 1;
                match++;
                s = match;
            }
            else return false;
        }
        while (p < len2 && pattern.charAt(p) == '*') p++;
        return p == len2;
    }
}

// ============================= DP ============================//

public class Solution {
    public boolean isMatch(String s, String p) {
        int slen = s.length(), plen = p.length();
        boolean[][] match = new boolean[slen + 1][plen + 1];
        match[slen][plen] = true;
        for (int i = plen - 1; i >= 0; i--) {
            if (p.charAt(i) != '*') break;
            else match[slen][i] = true;
        }
        for (int i = slen - 1; i >= 0; i--) {
            for (int j = plen - 1; j >= 0; j--) {
                if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')
                    match[i][j] = match[i + 1][j + 1];
                else if (p.charAt(j) == '*')
                    match[i][j] = match[i + 1][j] || match[i][j + 1];
                else match[i][j] = false;
            }
        }
        return match[0][0];
    }
}


