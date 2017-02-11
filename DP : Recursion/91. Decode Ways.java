// O(n) space
public class Solution {
    public int numDecodings(String s) {
        // two ways: 11~19, 21~26
        int n = s.length();
        if (n == 0) return 0;
        int[] dp = new int[n + 1];
        dp[n] = 1;
        dp[n - 1] = s.charAt(n - 1) == '0' ? 0 : 1;
        for (int i = n - 2; i >= 0; i--) {
            if (s.charAt(i) == '0' ) continue;
            else dp[i] = (Integer.parseInt(s.substring(i, i + 2)) <= 26) ? dp[i + 1] + dp[i + 2] : dp[i + 1];
        }
        return dp[0];
    }
}

// dp using O(n) space

public class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        char[] arr = s.toCharArray();
        if (arr[0] == '0') return 0;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            int val = (arr[i - 1] - '0') * 10 + arr[i] - '0';
            if (arr[i] == '0') dp[i] = arr[i - 1] == '0' || arr[i - 1] > '2' ? 0 : i > 1 ? dp[i - 2] : 1;
            else if ((val >= 21 && val <= 26) || (val >= 11 && val <= 19)) dp[i] = i > 1 ? dp[i - 1] + dp[i - 2] : dp[i - 1] + 1;
            else dp[i] = dp[i - 1];
        }
        return dp[n - 1];
    }
}

// dp using O(1) space

public class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        if (s.charAt(0) == '0') return 0;
        int pre = 1, res = 1;
        for (int i = 1; i < n; i++) {
            int last = res;
            if (s.charAt(i) == '0' && s.charAt(i - 1) != '1' && s.charAt(i - 1) != '2') return 0;
            int num = Integer.valueOf(s.substring(i - 1, i + 1));
            
            if (s.charAt(i) == '0') res = pre;
            else if ((num >= 11 && num <= 19) || (num <= 26 && num >= 21)) res += pre;
            pre = last;
        }
        return res;
    }
}
