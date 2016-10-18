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
