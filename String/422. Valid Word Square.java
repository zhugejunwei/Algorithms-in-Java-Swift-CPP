public class Solution {
    public boolean validWordSquare(List<String> words) {
        int n = words.size();
        if (words.size() != words.get(0).length()) return false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < words.get(i).length(); j++) {
                if (j >= n) return false; // words.get(i) is too long
                if (words.get(j).length() <= i) return false; // not satisfy `words.get(j).charAt(i)`
                if (words.get(i).charAt(j) != words.get(j).charAt(i)) return false;
            }
        }
        return true;
    }
}
