/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public int closestValue(TreeNode root, double target) {
        int a = root.val;
        TreeNode kid = target < root.val ? root.left : root.right;
        if (kid == null) return a;
        int b = closestValue(kid, target);
        return Math.abs(a - target) < Math.abs(b - target) ? a : b;
    }
}


// 

public class Solution {
    public int closestValue(TreeNode root, double target) {
        int pre = root.val;
        int kid = 0;
        if (target < root.val) {
            if (root.left == null) return pre;
            else kid = closestValue(root.left, target);
        } else {
            if (root.right == null) return pre;
            else kid = closestValue(root.right, target);
        }
        return Math.abs(target - pre) > Math.abs(target - kid) ? kid : pre;
    }
}
