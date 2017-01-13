public class Solution {
    public List<String> generatePalindromes(String s) {
        List<String> res = new ArrayList();
        if (s == null || s.length() == 0) return res;
        
        // count the characters in s
        int[] count = new int[128];
        for (char c : s.toCharArray()) count[c]++;
        
        int odd = 0;
        String mid = "";
        String myS = "";
        for (int i = 0; i < 128; i++) {
            if ((count[i] & 1) != 0) {
                // odd count
                odd++;
                mid += (char)i;
                // if there are more then one char whose count is odd, return []
                if (odd > 1) return res;
                
            }
            // add half count of char to myS
            for (int j = 0; j < count[i]/2; j++) {
                myS += (char)i;
            }
        }
        // use myS to backtrackingly form permutation
        helper(myS, res, "", new boolean[myS.length()]);
        
        // add another half
        for (int i = 0; i < res.size(); i++) {
            String t = res.get(i) + (mid == "" ? "" : mid) + (new StringBuilder(res.get(i)).reverse().toString());
            res.set(i, t);
        }
        return res;
    }
    
    // get permutations
    private void helper(String s, List<String> res, String tmp, boolean[] vis) {
        if (tmp.length() == s.length()) {
            res.add(tmp);
            return;
        }
        for (int i = 0; i < s.length(); i++) {
            if (vis[i] || (i > 0 && s.charAt(i) == s.charAt(i - 1) && !vis[i - 1])) continue;
            vis[i] = true;
            helper(s, res, tmp + s.charAt(i), vis);
            vis[i] = false;
        }
    }
}
