// slide window
public class Solution {
    public int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) return 0;
        int[] map = new int[128];
        int len = needle.length();
        for (char c : needle.toCharArray()) map[c]++;
        int start = 0, end = 0, count = needle.length();
        while (end < haystack.length()) {
            if (map[haystack.charAt(end++)]-- > 0) count--;
            if (count == 0 && needle.equals(haystack.substring(start, end))) return start;
            // when end - start == len, and if it's not the answer, move start forward
            if (end - start == len) if (map[haystack.charAt(start++)]++ >= 0) count++;
            // when meet a char that is not belongs to needle, move start forward
            while (map[haystack.charAt(end-1)] < 0) if (map[haystack.charAt(start++)]++ >= 0) count++;
        }
        return -1;
    }
}

// concise solution
public class Solution {
    public int strStr(String haystack, String needle) {
        for (int i = 0; ; i++) {
            for (int j = 0; ; j++) {
                if (j == needle.length()) return i;
                if (i + j == haystack.length()) return -1;
                if (haystack.charAt(i + j) != needle.charAt(j)) break;
            }
        }
    }
}
