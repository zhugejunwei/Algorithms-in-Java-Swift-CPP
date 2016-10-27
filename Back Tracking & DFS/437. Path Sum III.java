public class Solution {
    public int pathSum(TreeNode root, int sum) {
        // map<prefix Sum, ways to get to this prefix sum>
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, 1);
        return helper(root, 0, sum, map);
    }
    
    public int helper(TreeNode root, int preSum, int target, Map<Integer, Integer> map) {
        if (root == null) return 0;
        
        preSum += root.val;
        int res = map.getOrDefault(preSum - target, 0);
        map.put(preSum, map.getOrDefault(preSum, 0) + 1);
        
        res += helper(root.right, preSum, target, map) + helper(root.left, preSum, target, map);
        map.put(preSum, map.get(preSum) - 1);
        return res;
    }
}

