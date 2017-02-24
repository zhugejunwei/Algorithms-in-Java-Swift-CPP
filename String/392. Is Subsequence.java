// binary search, for many strings.
//
public class Solution {
    public boolean isSubsequence(String s, String t) {
        List<Integer>[] cache = new List[128];
        for (int i = 0; i < t.length(); i++) {
            if (cache[t.charAt(i)] == null)
                cache[t.charAt(i)] = new ArrayList();
            cache[t.charAt(i)].add(i);
        }
        
        int pre = 0, idx = 0;
        for (char c : s.toCharArray()) {
            if (cache[c] == null) return false;
            idx = Collections.binarySearch(cache[c], pre);
            if (idx < 0) idx = - idx - 1;
            if (idx == cache[c].size()) return false; // not find
            pre = cache[c].get(idx) + 1;
        }
        return true;
    }
}

// fastest solution
public class Solution {
    public boolean isSubsequence(String s, String t) {
        if (t.length() < s.length()) return false;
        int preIdx = 0;
        for (char cur : s.toCharArray()) {
            preIdx = t.indexOf(cur, preIdx); // check whether cur char exists after pre char.
            if (preIdx == -1) return false;
            preIdx++;
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
