public class Solution {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> res = new ArrayList();
        int cur = 1;
        for (int i = 1; i <= n; i++) {
            res.add(cur);
            if (cur * 10 <= n) {
                // 1 -> 10
                cur *= 10;
            } else if (cur % 10 != 9 && cur + 1 <= n) {
                // 10 -> 11
                cur++;
            } else {
                // 13 -> 2, first we need to skip "last digit == 9"
                while ((cur / 10) % 10 == 9) {
                    cur /= 10;
                }
                cur = cur / 10 + 1;
            }
        }
        return res;
    }
}
