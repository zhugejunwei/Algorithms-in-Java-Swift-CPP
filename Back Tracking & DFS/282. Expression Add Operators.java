public class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        helper(num, res, new StringBuilder(), target, 0, 0, 0);
        return res;
    }
    
    private void helper(String num, List<String> res, StringBuilder sb, int target, int start, long pre, long preMult) {
        if (start == num.length() && target == pre) {
            res.add(sb.toString());
            // return;
        }
        for (int i = start; i < num.length(); i++) {
            if (i != start && num.charAt(start) == '0') break; // a number can't start with '0'
            long cur = Long.parseLong(num.substring(start, i + 1));
            int len = sb.length();
            if (start == 0) {
                helper(num, res, sb.append(cur), target, i + 1, cur, cur);
                sb.setLength(len);
            } else {
                // +
                helper(num, res, sb.append("+").append(cur), target, i + 1, pre + cur, cur);
                sb.setLength(len);
                // -
                helper(num, res, sb.append("-").append(cur), target, i + 1, pre - cur, -cur);
                sb.setLength(len);
                // *
                // A + B * C = A + B - B + B * C
                helper(num, res, sb.append("*").append(cur), target, i + 1, pre - preMult + preMult * cur, cur * preMult);
                sb.setLength(len);
            }
        }
    }
}
