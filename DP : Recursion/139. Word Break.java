// dp - baisc

// first loop: len 1...n
// second loop: start position i: 0...i + len - 1 < n
//              end position j = i + len - 1
// third loop: k from i to j

public class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        Set<String> dict = new HashSet(wordDict);
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = i - 1; j >= 0; j--) { // i-1->0 is faster than 0->i-1
                dp[i] = dict.contains(s.substring(j, i)) && dp[j];
                if (dp[i]) break;
            }
        }
        return dp[s.length()];
    }
}

// dp - first solution
public class Solution {
    public boolean wordBreak(String s, Set<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (String d : wordDict) {
                if (d.length() <= i) {
                    if (dp[i - d.length()]) {
                        if (s.substring(i - d.length(), i).equals(d)) {
                            dp[i] = true;
                            break;
                        }
                    }
                }
            }
        }
        return dp[s.length()];
    }
}

// dp - 2nd solution
public class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int len = 1; len <= n; len++) {
            for (int i = len - 1; i >= 0; i--) {
                if (wordDict.contains(s.substring(i, len))) dp[len] = dp[i];
                if (dp[len]) break;
            }
        }
        return dp[n];
    }
}

// dp - 3
public class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}
