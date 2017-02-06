public class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String res = strs[0];
        for (String s : strs) {
            int i = 0;
            while (i < s.length() && i < res.length() && s.charAt(i) == res.charAt(i)) {
                i++;
            }
            if (i < res.length()) res = res.substring(0, i);
        }
        return res;
    }
}
