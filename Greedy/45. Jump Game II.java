public class Solution {
    public int jump(int[] nums) {
        int curEnd = 0, curFarthest = 0, steps = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            curFarthest = Math.max(curFarthest, nums[i] + i);
            if (i == curEnd) {
                steps++;
                curEnd = curFarthest;
            }
        }
        return steps;
    }
}

// ================================ BFS ================================= //

public class Solution {
    public int jump(int[] nums) {
        if (nums.length < 2) return 0;
        int level = 0, curMax = 0, nextMax = 0, i = 0;
        while (curMax - i + 1 > 0) {
            level++;
            for (; i <= curMax; i++) {
                nextMax = Math.max(nextMax, (i + nums[i]));
                if (nextMax >= nums.length - 1) return level;
            }
            curMax = nextMax;
        }
        return Integer.MAX_VALUE;
    }
}
