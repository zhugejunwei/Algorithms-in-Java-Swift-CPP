// Iteration

public class Solution {
    public boolean isValidBST(TreeNode root) {
        List<Integer> list = new ArrayList();
        Deque<TreeNode> stack = new ArrayDeque();
        TreeNode t = root;
        while (t != null || !stack.isEmpty()) {
            if (t != null) {
                stack.push(t);
                t = t.left;
            } else {
                TreeNode node = stack.pop();
                if (!list.isEmpty() && node.val <= list.get(list.size() - 1)) return false;
                list.add(node.val);
                t = node.right;
            }
        }
        return true;
    }
}

// ============================= Recursion ============================= //

public class Solution {
    public boolean isValidBST(TreeNode root) {
        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    private boolean isValid(TreeNode root, long min, long max) {
        if (root == null) return true;
        if (root.val >= max || root.val <= min) return false;
        return isValid(root.left, min, root.val) && isValid(root.right, root.val, max);
    }
}

// recursion

public class Solution {
    TreeNode pre = null;
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        if (!isValidBST(root.left)) return false;
        if (pre != null && pre.val >= root.val) return false;
        pre = root;
        return isValidBST(root.right);
    }
}


// ========================== Inorder Traversal ============================= //

public class Solution {
    TreeNode invalidNode = new TreeNode(Integer.MAX_VALUE);
    
    public boolean isValidBST(TreeNode root) {
        return isValid(root, null) != invalidNode;
    }
    
    private TreeNode isValid(TreeNode root, TreeNode pre) {
        if (root == null) return pre;
        pre = isValid(root.left, pre);
        if (pre != null && pre.val >= root.val) return invalidNode;
        return isValid(root.right, root);
    }
}


