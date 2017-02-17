// binary search, for many string s.
//
public class Solution {
    public boolean isSubsequence(String s, String t) {
        if (s == null || s.length() == 0) return true;
        List<Integer>[] map = new List[128];
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (map[c] == null)
                map[c] = new ArrayList();
            map[c].add(i);
        }
        
        int pre = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map[s.charAt(i)] == null) return false;
            int j = Collections.binarySearch(map[s.charAt(i)], pre);
            if (j < 0) j = -j - 1;
            if (j == map[s.charAt(i)].size()) return false;
            pre = map[s.charAt(i)].get(j) + 1;
        }
        return true;
    }
}

// another solution for one S.
public class Solution {
    public boolean isSubsequence(String s, String t) {
        if (s.length() == 0 || (s.length() == 0 && t.length() == 0)) return true;
        if (s.length() != 0 && t.length() == 0) return false;
        char[] sa = s.toCharArray();
        char[] ta = t.toCharArray();
        int i = 0, j = 0;
        while (i < ta.length) {
            if (ta[i] == sa[j]) { j++; i++; }
            else i++;
            if (j == sa.length) return true;
            if (i == ta.length && j != sa.length) return false;
        }
        return false;
    }
}


/* follow up
If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want to check one by one to see if T has its subsequence. In this scenario, how would you change your code?
*/

/*
 查这个word以前查过的最长prefix是多少，如果query 1B times，大概也就前几千个词有可能出现没任何prefix处理过的情况
 那node里我觉得可以存a,1 -> b,3 -> c,6
 然后下个word， “ca”, 就是 c,6 -> a,7. 每次有word来先去trie里过一遍，返回一个最大的prefix的idx，然后可以去他建的那个idx的array里找剩下的。。。
*/
