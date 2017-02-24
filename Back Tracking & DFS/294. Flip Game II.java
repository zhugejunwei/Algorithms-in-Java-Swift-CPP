// better
public class Solution {
    public boolean canWin(String s) {
        return canWin(s.toCharArray());
    }
    private boolean canWin(char[] mark) {
        for (int i = 0; i < mark.length - 1; i++) {
            if (mark[i] == '+' && mark[i + 1] == '+') {
                mark[i] = '-';
                mark[i + 1] = '-';
                // if other person cannot win, current person win.
                boolean win = !canWin(mark);
                mark[i] = '+';
                mark[i + 1] = '+';
                if (win) return true;
            }
        }
        // current person cannot flip
        return false;
    }
}

// with memo, faster
public class Solution {
    public boolean canWin(String s) {
        return canWin(s.toCharArray(), new HashMap());
    }
    
    private boolean canWin(char[] s, Map<String, Boolean> map) {
        String key = String.valueOf(s);
        if (map.containsKey(key)) return map.get(key);
        for (int i = 0; i < s.length - 1; i++) {
            if (s[i] == '+' && s[i + 1] == '+') {
                s[i] = '-';
                s[i + 1] = '-';
                boolean win = !canWin(s, map);
                s[i] = '+';
                s[i + 1] = '+';
                if (win) {
                    map.put(String.valueOf(s), true);
                    return true;
                }
            }
        }
        map.put(String.valueOf(s), false);
        return false;
    }
}

// The solution below needs many theoretical concepts to understand.
// https://discuss.leetcode.com/topic/27282/theory-matters-from-backtracking-128ms-to-dp-0ms/5
public class Solution {
    public boolean canWin(String s) {
        s = s.replace('-', ' ');
        int G = 0;
        List<Integer> g = new ArrayList<>();
        for (String t : s.split("[ ]+")) {
            int p = t.length();
            if (p == 0) continue;
            while (g.size() <= p) {
                char[] x = t.toCharArray();
                int i = 0, j = g.size() - 2;
                while (i <= j)
                    x[g.get(i++) ^ g.get(j--)] = '-';
                g.add(new String(x).indexOf('+'));
            }
            G ^= g.get(p);
        }
        return G != 0;
    }
}
