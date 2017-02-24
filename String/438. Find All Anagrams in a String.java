public class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList();
        if (s.length() == 0 || s == null) return list;
        int[] map = new int[128];
        for (char c : p.toCharArray()) map[c]++;
        int count = p.length(), start = 0, end = 0;
        while (end < s.length()) {
            if (map[s.charAt(end++)]-- >= 1) count--;
            if (count == 0) list.add(start);
            // slide window, one by one, move one char at a time
            if (end - start == p.length() && map[s.charAt(start++)]++ >= 0) count++;
        }
        return list;
    }
}

// style_2
public class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList();
        int[] map = new int[128];
        for (char c : p.toCharArray()) map[c]++;
        int start = 0, end = 0, count = p.length();
        while (end < s.length()) {
            if (map[s.charAt(end++)]-- > 0) count--;
            while (count == 0) {
                if (end - start == p.length()) res.add(start);
                if (map[s.charAt(start++)]++ >= 0) count++;
            }
        }
        return res;
    }
}
