public class Solution
{
    // 2,3,4,5,6,7,8,9
    String[] map = { "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
    List<String> res = new ArrayList<>();
    
    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) return res;
        List<Character> list = new ArrayList<>();
        DFS(digits, 0, list);
        return res;
    }
    // DFS
    public void DFS(String digits, int row, List<Character> list) {
        int i = digits.charAt(row) - '2';
        for (char c : map[i].toCharArray()) {
            list.add(c);
            if (row == digits.length() - 1) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < list.size(); j++) {
                    sb.append(list.get(j));
                }
                res.add(sb.toString());
            }
            else
                DFS(digits, row + 1, list);
            list.remove(list.size()-1);
        }
    }
}

// simple backtracking
public class Solution {
    String[] letters = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList();
        if (digits.length() == 0) return res;
        dfs(digits, res, "", 0);
        return res;
    }
    
    private void dfs(String s, List<String> res, String tmp, int i) {
        if (i > s.length()) return;
        if (i == s.length()) {
            res.add(new String(tmp));
            return;
        }
        int num = Character.getNumericValue(s.charAt(i));
        for (int j = 0; j < letters[num].length(); j++) {
            dfs(s, res, tmp + letters[num].charAt(j), i + 1);
        }
    }
}
