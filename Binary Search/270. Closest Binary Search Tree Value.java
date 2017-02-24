/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// solution_1, same as solution_2 and 3
public class Solution {
    public int closestValue(TreeNode root, double target) {
        int a = root.val;
        TreeNode kid = target < root.val ? root.left : root.right;
        if (kid == null) return a;
        int b = closestValue(kid, target); // this is the closestValue of left or right children
        return Math.abs(a - target) < Math.abs(b - target) ? a : b;
    }
}


// solution_2

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


// solution_3
public class Solution {
    public int closestValue(TreeNode root, double target) {
        if (target > root.val) {
            if (root.right == null) return root.val;
            if (Math.abs(root.val - target) < Math.abs(getRightMin(root.right) - target)) return root.val;
            return closestValue(root.right, target);
        } else if (target < root.val) {
            if (root.left == null || Math.abs(root.val - target) < Math.abs(getLeftMax(root.left) - target)) return root.val;
            return closestValue(root.left, target);
        } else {
            return root.val;
        }
    }
    
    private int getRightMin(TreeNode node) {
        while (node.left != null) node = node.left;
        return node.val;
    }
    
    private int getLeftMax(TreeNode node) {
        while (node.right != null) node = node.right;
        return node.val;
    }
}
