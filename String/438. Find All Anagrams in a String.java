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
            // slide window
            if (end - start == p.length() && map[s.charAt(start++)]++ >= 0) count++;
        }
        return list;
    }
}
