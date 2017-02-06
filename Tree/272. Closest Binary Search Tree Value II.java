public class Solution {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> res = new ArrayList();
        Deque<Integer> pre = new ArrayDeque(); // predecessor
        Deque<Integer> suc = new ArrayDeque(); // successor
        
        inorder(root, target, pre, false); // predecessor
        inorder(root, target, suc, true); // successor
        
        while (k-- > 0) {
            if (pre.isEmpty()) {
                res.add(suc.pop());
            } else if (suc.isEmpty()) {
                res.add(pre.pop());
            } else if (Math.abs(pre.peek() - target) < Math.abs(suc.peek() - target)) {
                res.add(pre.pop());
            } else {
                res.add(suc.pop());
            }
        }
        return res;
    }
    
    private void inorder(TreeNode root, double target, Deque<Integer> stack, boolean reversed) {
        if (root == null) return;
        inorder(reversed ? root.right : root.left, target, stack, reversed);
        if ((reversed && root.val <= target) || (!reversed && root.val > target)) return;
        stack.push(root.val);
        inorder(reversed ? root.left : root.right, target , stack, reversed);
    }
}

// 

public class Solution {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> res = new ArrayList();
        TreeNode closest = findClosestNode(root, target);
        res.add(closest.val);
        TreeNode pre = getPredecessor(root, closest);
        TreeNode suc = getSuccessor(root, closest);
        while (k > 1) {
            if (pre == null) {
                res.add(suc.val);
                suc = getSuccessor(root, suc);
            } else if (suc == null) {
                res.add(pre.val);
                pre = getPredecessor(root, pre);
            } else {
                if (Math.abs(pre.val - target) < Math.abs(suc.val - target)) {
                    res.add(pre.val);
                    pre = getPredecessor(root, pre);
                } else {
                    res.add(suc.val);
                    suc = getSuccessor(root, suc);
                }
            }
            k--;
        }
        return res;
    }
    
    private TreeNode findClosestNode(TreeNode root, double target) {
        TreeNode closer = target > root.val ? root.right : root.left;
        if (closer == null) return root;
        TreeNode kid = findClosestNode(closer, target);
        return Math.abs(kid.val - target) < Math.abs(root.val - target) ? kid : root;
    }
    
    private TreeNode getPredecessor(TreeNode root, TreeNode p) {
        // left-side right most
        if (root == null) return null;
        
        if (p.val >= root.val) {
            // right most node in the leftside
            return getPredecessor(root.right, p);
        } else {
            // left side
            TreeNode left = getPredecessor(root.left, p);
            return left == null ? root : left;
        }
    }
    
    private TreeNode getSuccessor(TreeNode root, TreeNode p) {
        // right-side left most node
        if (root == null) return null;
        
        if (p.val <= root.val) {
            // left most node
            return getSuccessor(root.left, p);
        } else {
            // right side
            TreeNode right = getSuccessor(root.right, p);
            return right == null ? root : right;
        }
    }
}
