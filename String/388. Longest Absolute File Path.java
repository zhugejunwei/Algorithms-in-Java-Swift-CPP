public class Solution {
    public int lengthLongestPath(String input) {
        int maxLen = 0;
        Deque<Integer> stack = new ArrayDeque();
        stack.push(0);
        for (String s : input.split("\n")) {
            int level = s.lastIndexOf("\t") + 1;  // number of "\t"
            while (level + 1 < stack.size()) stack.pop(); // find parent
            stack.push(stack.peek() + s.length() + 1 - level);
            if (s.contains(".")) maxLen = Math.max(stack.peek() - 1, maxLen);
        }
        return maxLen;
    }
}

// shorter, dp solution
public class Solution {
    public int lengthLongestPath(String input) {
        int maxLen = 0;
        String[] str = input.split("\n");
        int[] stack = new int[str.length + 1];
        for (String s : str) {
            int level = s.lastIndexOf("\t") + 1;
            int curLen = stack[level + 1] = stack[level] + s.length() + 1 - level;
            if (s.contains(".")) maxLen = Math.max(curLen - 1, maxLen);
        }
        return maxLen;
    }
}
