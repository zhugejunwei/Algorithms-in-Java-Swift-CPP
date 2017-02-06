public class Solution {
    public String decodeString(String s) {
        Deque<String> strStack = new ArrayDeque();
        Deque<Integer> intStack = new ArrayDeque();
        String cur = "";
        int val = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                val = val * 10 + s.charAt(i) - '0';
            } else if (s.charAt(i) == '[') {
                strStack.push(cur);
                cur = new String("");
                intStack.push(val);
                val = 0;
            } else if (s.charAt(i) == ']') {
                String tmp = cur;
                cur = strStack.pop();
                for (int j = intStack.pop(); j > 0; j--) {
                    cur += tmp;
                }
            } else cur += s.charAt(i);
        }
        return cur;
    }
}
