public class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList();
        backtrack(num, target, res, "", 0, 0, 0);
        return res;
    }
    private void backtrack(String num, int target, List<String> res, String s, int start, long preSum, long preMul) {
        if (start == num.length()) {
            if (preSum == target) {
                res.add(new String(s));
            }
            return;
        }
        for (int i = start; i < num.length(); i++) {
            // a number cannot start with 0
            if (i != start && num.charAt(start) == '0') break;
            // get the current value
            long cur = Long.valueOf(num.substring(start, i + 1));
            // beginning
            if (start == 0) {
                backtrack(num, target, res, s + cur, i + 1, cur, cur);
            } else {
                // +
                backtrack(num, target, res, s + "+" + cur, i + 1, preSum + cur, cur);
                // -
                backtrack(num, target, res, s + "-" + cur, i + 1, preSum - cur, -cur);
                // *
                backtrack(num, target, res, s + "*" + cur, i + 1, preSum - preMul + preMul * cur, preMul * cur);
            }
        }
    }
}
