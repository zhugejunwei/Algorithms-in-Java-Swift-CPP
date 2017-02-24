public class Solution {
    public int lengthLongestPath(String input) {
        Deque<Integer> stack = new ArrayDeque();
        stack.push(0); // pre level = 0
        int max = 0;
        for (String s : input.split("\n")) {
            int level = s.lastIndexOf("\t") + 1;
            // using stack.size() to represent the pre level 
            while (stack.size() - 1 > level)
                stack.pop();
            int len = stack.peek() + s.length() + 1 - level; // remove '\t', add "/"
            stack.push(len);
            if (s.contains(".")) max = Math.max(max, len - 1); // remove last "/"
        }
        return max;
    }
}
