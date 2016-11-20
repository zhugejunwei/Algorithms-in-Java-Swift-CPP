public class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> res = new ArrayList();
        int len = strings.length;
        boolean[] vis = new boolean[len];
        for (int i = 0; i < len; i++) {
            if (vis[i]) continue;
            List<String> list = new ArrayList();
            list.add(strings[i]);
            for (int j = i+1; j < len; j++) {
                if (vis[j] || strings[i].length() != strings[j].length()) continue;
                int k = 0;
                int dif = (strings[i].charAt(k) - strings[j].charAt(k) + 26)%26;
                while (k < strings[i].length() && (strings[i].charAt(k) - strings[j].charAt(k) + 26)%26 == dif) {
                    k++;
                }
                if (k == strings[j].length()) {
                    vis[j] = true;
                    list.add(strings[j]);
                }
            }
            res.add(new ArrayList(list));
        }
        return res;
    }
}
