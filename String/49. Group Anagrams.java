public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap();
        int n = strs.length;
        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String keyStr = String.valueOf(chars);
            if (!map.containsKey(keyStr)) map.put(keyStr, new ArrayList());
            map.get(keyStr).add(s);
        }
        return new ArrayList<List<String>>(map.values());
    }
}

// faster solution 2, using counting sort (below is in c++)
string strSort(string& s) {
    int count[26] = {0}, n = s.length();
    for (int i = 0; i < n; i++)
        count[s[i] - 'a']++;
    int p = 0;
    string t(n, 'a');
    for (int j = 0; j < 26; j++)
        for (int i = 0; i < count[j]; i++)
            t[p++] += j;
    return t;
}
