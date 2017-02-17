public class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList();
        int n = s.length(), m = words.length, k;
        if (n == 0 || m == 0 || (k = words[0].length()) == 0) return res;
        
        Map<String, Integer> dict = new HashMap();
        for (String word : words)
            dict.put(word, dict.getOrDefault(word, 0) + 1);
        
        Map<String, Integer> curdict = new HashMap();
        
        int start = 0, len = m * k;
        String test, tmp;
        for (int i = 0; i < k; i++) {
            curdict.clear();
            start = i;
            if (start + len > n) return res;
            for (int j = i; j + k <= n; j += k) {
                test = s.substring(j, j + k);
                if (dict.containsKey(test)) {
                    if (!curdict.containsKey(test) || curdict.get(test) < dict.get(test)) {
                        curdict.put(test, curdict.getOrDefault(test, 0) + 1);
                        if (start + len == j + k) {
                            res.add(start);
                            
                            // remove s.substring(start, start + k)
                            decreaseCount(curdict, s.substring(start, start + k));
                            
                            // move window to right, move start to start + k
                            start += k;
                        }
                        continue;
                    }
                    
                    // if curdict.get(test) == dict.get(test)
                    // find the first substring that equals test
                    while (!(tmp = s.substring(start, start + k)).equals(test)) {
                        decreaseCount(curdict, tmp);
                        start += k;
                    }
                    // and skip one substring that equals test
                    start += k;
                    if (start + len > n) break;
                    continue;
                }
                // if dict not contains test, move start k steps right
                start = j + k;
                if (start + len > n) break;
                curdict.clear();
            }
        }
        return res;
    }
    
    private void decreaseCount(Map<String ,Integer> curdict, String tmp) {
        if (curdict.get(tmp) == 1)
            curdict.remove(tmp);
        else
            curdict.put(tmp, curdict.get(tmp) - 1);
    }
}
