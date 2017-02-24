// O(n) solution
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

// O(klog(n)) solution
public class Solution {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        // find closest node
        TreeNode closest = helper(root, target);
        
        // find results
        List<Integer> res = new ArrayList();
        res.add(closest.val);
        // pre node and suc node
        TreeNode pre = getPredecessor(root, closest), suc = getSuccessor(root, closest);
        
        for (int i = 1; i < k; i++) {
            if (pre == null) {
                res.add(suc.val);
                suc = getSuccessor(root, suc);
            } else if (suc == null) {
                res.add(pre.val);
                pre = getPredecessor(root, pre);
            } else {
                if (Math.abs(pre.val - target) > Math.abs(suc.val - target)) {
                    res.add(suc.val);
                    suc = getSuccessor(root, suc);
                } else {
                    res.add(pre.val);
                    pre = getPredecessor(root, pre);
                }
            }
        }
        return res;
    }
    
    private TreeNode helper(TreeNode root, double target) {
        TreeNode closer = root.val > target ? root.left : root.right;
        if (closer == null) return root;
        TreeNode kid = helper(closer, target);
        return Math.abs(root.val - target) < Math.abs(kid.val - target) ? root : kid;
    }
    
    // "left", root, right
    private TreeNode getPredecessor(TreeNode root, TreeNode p) {
        if (root == null) return null;
        
        if (root.val >= p.val) {
            return getPredecessor(root.left, p);
        } else {
            TreeNode right = getPredecessor(root.right, p);
            return right != null ? right : root;
        }
    }
    
    // left, root, "right"
    private TreeNode getSuccessor(TreeNode root, TreeNode p) {
        if (root == null) return null;
        
        if (root.val <= p.val) {
            return getSuccessor(root.right, p);
        } else {
            TreeNode left = getSuccessor(root.left, p);
            return left != null ? left : root;
        }
    }
}
