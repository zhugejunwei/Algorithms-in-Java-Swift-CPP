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

// a little bit faster
public class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> res = new ArrayList();
        int n = strings.length;
        boolean[] vis = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (vis[i]) continue;
            vis[i] = true;
            List<String> list = new ArrayList();
            String a = strings[i];
            list.add(a);
            for (int j = i + 1; j < n; j++) {
                if (vis[j] || strings[j].length() != a.length()) continue;
                String b = strings[j];
                int k = 0;
                int dif = (a.charAt(k) - b.charAt(k) + 26)%26;
                if (b.length() == 1) {
                    list.add(b);
                    vis[j] = true;
                }
                else {
                    while (k < a.length() && dif == (a.charAt(k) - b.charAt(k) + 26)%26) {
                        k++;
                    }
                    if (k == a.length()) {
                        list.add(b);
                        vis[j] = true;
                    }
                }
            }
            res.add(list);
        }
        return res;
    }
}
