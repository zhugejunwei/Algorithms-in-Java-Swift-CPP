public class Solution {
    public static String frequencySort(String s) {
        if (s.length() == 0 || s == null) return "";
        char[] ss = s.toCharArray();
        int[] count = new int[128];
        for (char c : ss) {
            count[c]++;
        }
        Map<Integer, List<Character>> map = new HashMap<>();
        for (int i = 0; i < 128; i++) {
            if (count[i] == 0) continue;
            if (!map.containsKey(count[i])) {
                map.put(count[i], new ArrayList<Character>());
            }
            map.get(count[i]).add((char)i);
        }
        StringBuilder res = new StringBuilder();
        for (int i = ss.length; i > 0; i--) {
            if (!map.containsKey(i)) continue;
            List<Character> list = map.get(i);
            for (Character c : list) {
                for (int cnt = 0; cnt < i; cnt++) {
                    res.append(c);
                }
            }
        }
        return res.toString();
    }
}


//
public class Solution {
    public static String frequencySort(String s) {
        int[] count = new int[128]; // char : count
        int maxCount = 0;
        for (char c : s.toCharArray()) {
            count[c]++;
            maxCount = Math.max(count[c], maxCount);
        }
        List<Character>[] map = new List[maxCount + 1]; // count : char
        for (int i = 0; i < 128; i++) {
            if (count[i] == 0) continue;
            if (map[count[i]] == null) {
                map[count[i]] = new ArrayList();
            }
            for (int j = 0; j < count[i]; j++)
                map[count[i]].add((char)i);
        }
        StringBuilder res = new StringBuilder();
        for (int i = maxCount; i >= 0; i--) {
            if (map[i] != null) {
                for (int j = 0; j < map[i].size(); j++) {
                    res.append(map[i].get(j));
                }
            }
            
        }
        return res.toString();
    }
}
